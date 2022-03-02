package com.example.taco.JMS;


import org.apache.activemq.artemis.api.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Service;

import com.example.taco.domain.Order;

import javax.jms.JMSException;


@Service
public class JmsOrderMessagingService implements  OrderMessagingService{

    private JmsTemplate jms;


    @Autowired
    public JmsOrderMessagingService(JmsTemplate jms ) {
        this.jms = jms;
    }

    @Override
    public void sendOrder(Order order) {
        jms.convertAndSend("tacocloud.order.queue", order,
                message -> {
                    message.setStringProperty("X_ORDER_SOURCE", "WEB");
                    return message;
                });
    }


}


