package com.example.taco.repository;

import com.example.taco.domain.Order;

public interface OrderRepository {
    Order save(Order order);
}
