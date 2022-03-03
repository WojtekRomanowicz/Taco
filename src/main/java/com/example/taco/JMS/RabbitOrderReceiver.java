package com.example.taco.JMS;

import com.example.taco.domain.Order;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitOrderReceiver implements OrderReceiver{

    private RabbitTemplate rabbit;
    private MessageConverter converter;

    @Autowired
    public RabbitOrderReceiver(RabbitTemplate rabbit) {
        this.rabbit = rabbit;
        this.converter = rabbit.getMessageConverter();
    }
    
    public Order receiveOrder() {
        Message message = rabbit.receive("tacocloud.orders");
        return message != null
                ? (Order) converter.fromMessage(message)
                : null;
    }
}
