package com.finalproject.restaurent.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finalproject.restaurent.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByTableNumber(int tableNumber);

    List<Order> findByStatus(String status);

    List<Order> findByCreatedAtBetween(java.time.LocalDateTime start, java.time.LocalDateTime end);
}
