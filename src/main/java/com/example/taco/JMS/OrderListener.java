package com.example.taco.JMS;

import com.example.taco.domain.Order;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {
    private KitchenUI ui;

    @Autowired
    public OrderListener(KitchenUI ui) {
        this.ui = ui;
    }

   // @RabbitListener(queues = "tacocloud.order.queue")
    @JmsListener(destination = "tacocloud.order.queue")
    public void receiveOrder(Order order) {
        ui.displayOrder(order);
    }

    @KafkaListener(topics="tacocloud.orders.topic")
    public void handle(Order order) {
        ui.displayOrder(order);
    }
}