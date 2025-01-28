package com.mybookstore.mybookstore.Services;

import org.springframework.stereotype.Service;

import com.mybookstore.mybookstore.Repositories.OrderRepository;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository; 
    }

    
}
