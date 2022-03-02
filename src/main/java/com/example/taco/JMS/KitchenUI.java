package com.example.taco.JMS;

import com.example.taco.domain.Order;
import org.springframework.stereotype.Component;

@Component
public class KitchenUI {


    public String displayOrder(Order order){
        return order.toString();
    }
}
