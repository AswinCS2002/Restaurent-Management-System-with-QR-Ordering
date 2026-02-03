package com.finalproject.restaurent.service;

import java.util.List;

import com.finalproject.restaurent.dto.OrderRequestDTO;
import com.finalproject.restaurent.entity.Order;
import com.finalproject.restaurent.enums.OrderStatus;

public interface OrderService {
	Order placeOrder(OrderRequestDTO request);

	Order getOrderById(Long id);

	List<Order> getAllOrders();

	List<Order> getOrdersByTable(int tableNumber);

	Order updateOrderStatus(Long id, OrderStatus status);

	java.util.Map<String, Object> getTodayStats();
}
