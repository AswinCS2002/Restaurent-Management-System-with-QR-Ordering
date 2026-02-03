package com.finalproject.restaurent.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.restaurent.entity.MenuItem;
import com.finalproject.restaurent.service.AdminMenuService;

@RestController
@RequestMapping("/api/admin/menu")
public class AdminMenuController {
    private final AdminMenuService adminMenuService;

    public AdminMenuController(AdminMenuService adminMenuService) {
        this.adminMenuService = adminMenuService;
    }

    @PostMapping
    public MenuItem createMenuItem(@RequestBody MenuItem menuItem) {
        return adminMenuService.createMenuItem(menuItem);
    }

    @PutMapping("/{id}")
    public MenuItem updateMenuItem(
            @PathVariable Long id,
            @RequestBody MenuItem menuItem) {
        return adminMenuService.updateMenuItem(id, menuItem);
    }

    @DeleteMapping("/{id}")
    public void deleteMenuItem(@PathVariable Long id) {
        adminMenuService.deleteMenuItem(id);
    }

    @GetMapping
    public List<MenuItem> getAllMenuItems() {
        return adminMenuService.getAllMenuItems();
    }
}
