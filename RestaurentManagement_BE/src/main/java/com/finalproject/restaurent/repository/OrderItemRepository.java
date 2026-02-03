package com.finalproject.restaurent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finalproject.restaurent.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long>{

}

