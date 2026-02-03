package com.finalproject.restaurent.controller;

import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final com.finalproject.restaurent.service.OrderService orderService;

    public AdminController(com.finalproject.restaurent.service.OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        Map<String, Object> response = new HashMap<>();
        // Simple hardcoded check for demonstration
        if ("admin".equals(username) && "admin".equals(password)) {
            response.put("success", true);
            response.put("message", "Login successful");
        } else {
            response.put("success", false);
            response.put("message", "Invalid credentials");
        }
        return response;
    }

    @GetMapping("/stats")
    public Map<String, Object> getDailyStats() {
        return orderService.getTodayStats();
    }
}
