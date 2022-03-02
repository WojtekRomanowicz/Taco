package com.example.taco.JMS;

import com.example.taco.domain.Order;

public interface OrderMessagingService {

    void sendOrder(Order order);
}
