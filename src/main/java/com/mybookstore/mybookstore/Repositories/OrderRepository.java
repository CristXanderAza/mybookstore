package com.mybookstore.mybookstore.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mybookstore.mybookstore.Entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
