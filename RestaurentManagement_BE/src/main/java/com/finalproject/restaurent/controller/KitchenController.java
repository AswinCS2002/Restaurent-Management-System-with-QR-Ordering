package com.finalproject.restaurent.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.restaurent.entity.MenuItem;
import com.finalproject.restaurent.entity.Order;
import com.finalproject.restaurent.enums.OrderStatus;
import com.finalproject.restaurent.service.KitchenService;
import com.finalproject.restaurent.service.OrderService;
import java.util.Map;
import java.util.HashMap;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/kitchen")
public class KitchenController {

    private final KitchenService kitchenService;
    private final OrderService orderService;;

    public KitchenController(KitchenService kitchenService,
            OrderService orderService) {
        this.kitchenService = kitchenService;
        this.orderService = orderService;
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        Map<String, Object> response = new HashMap<>();
        // Simple hardcoded check for demonstration
        if ("kitchen".equals(username) && "kitchen".equals(password)) {
            response.put("success", true);
            response.put("message", "Login successful");
        } else {
            response.put("success", false);
            response.put("message", "Invalid credentials");
        }
        return response;
    }

    @GetMapping("/menu")
    public List<MenuItem> getMenuItems() {
        return kitchenService.getActiveMenuItems();
    }

    @PutMapping("/menu/{id}/available")
    public MenuItem markAvailable(@PathVariable Long id) {
        return kitchenService.markAvailable(id);
    }

    @PutMapping("/menu/{id}/unavailable")
    public MenuItem markUnavailable(@PathVariable Long id) {
        return kitchenService.markUnavailable(id);
    }

    @GetMapping("/orders")
    public List<Order> getPendingOrders() {
        return orderService.getAllOrders();
    }

    @PutMapping("/orders/{id}/status")
    public Order updateOrderStatus(
            @PathVariable Long id,
            @RequestBody java.util.Map<String, String> body) {
        String statusStr = body.get("status");
        OrderStatus status = OrderStatus.valueOf(statusStr.toUpperCase());
        return orderService.updateOrderStatus(id, status);
    }

}
