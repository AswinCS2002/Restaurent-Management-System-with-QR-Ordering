
package com.finalproject.restaurent.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.restaurent.dto.OrderRequestDTO;
import com.finalproject.restaurent.entity.Order;
import com.finalproject.restaurent.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Order placeOrder(@RequestBody OrderRequestDTO request) {
        return orderService.placeOrder(request);
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @GetMapping("/table/{tableNumber}")
    public List<Order> getOrdersByTable(@PathVariable int tableNumber) {
        return orderService.getOrdersByTable(tableNumber);
    }
}
