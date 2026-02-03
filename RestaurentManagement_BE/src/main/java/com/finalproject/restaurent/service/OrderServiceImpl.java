package com.finalproject.restaurent.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.finalproject.restaurent.dto.OrderItemRequestDTO;
import com.finalproject.restaurent.dto.OrderRequestDTO;
import com.finalproject.restaurent.entity.MenuItem;
import com.finalproject.restaurent.entity.Order;
import com.finalproject.restaurent.entity.OrderItem;
import com.finalproject.restaurent.enums.OrderStatus;
import com.finalproject.restaurent.exception.ResourceNotFoundException;
import com.finalproject.restaurent.repository.MenuItemRepository;
import com.finalproject.restaurent.repository.OrderItemRepository;
import com.finalproject.restaurent.repository.OrderRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final MenuItemRepository menuItemRepository;

    public OrderServiceImpl(OrderRepository orderRepository,
            OrderItemRepository orderItemRepository,
            MenuItemRepository menuItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public Order placeOrder(OrderRequestDTO request) {

        Order order = new Order();
        order.setTableNumber(request.getTableNumber());
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);

        int total = 0;
        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemRequestDTO dto : request.getItems()) {

            MenuItem menuItem = menuItemRepository.findById(dto.getMenuItemId())
                    .orElseThrow(() -> new ResourceNotFoundException("Menu item not found"));

            if (!menuItem.isAvailable() || !menuItem.isActive()) {
                throw new RuntimeException("Menu item not available");
            }

            OrderItem item = new OrderItem();
            item.setMenuItem(menuItem);
            item.setQuantity(dto.getQuantity());
            item.setPriceAtOrder(menuItem.getPrice());
            item.setOrder(order);

            total += menuItem.getPrice() * dto.getQuantity();
            orderItems.add(item);
        }

        order.setTotalAmount(total);
        order.setItems(orderItems);

        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getOrdersByTable(int tableNumber) {
        return orderRepository.findByTableNumber(tableNumber);
    }

    @Override
    public Order updateOrderStatus(Long id, OrderStatus status) {
        Order order = getOrderById(id);
        order.setStatus(status);
        return orderRepository.save(order);
    }

    @Override
    public java.util.Map<String, Object> getTodayStats() {
        java.time.LocalDateTime start = java.time.LocalDate.now().atStartOfDay();
        java.time.LocalDateTime end = java.time.LocalDate.now().atTime(java.time.LocalTime.MAX);

        List<Order> todayOrders = orderRepository.findByCreatedAtBetween(start, end);

        int totalSales = todayOrders.size();
        int totalIncome = todayOrders.stream()
                .filter(o -> o.getStatus() != OrderStatus.CANCELLED)
                .mapToInt(Order::getTotalAmount)
                .sum();

        java.util.Map<String, Object> stats = new java.util.HashMap<>();
        stats.put("totalOrders", totalSales);
        stats.put("totalIncome", totalIncome);
        return stats;
    }
}
