package com.finalproject.restaurent.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.restaurent.entity.MenuItem;
import com.finalproject.restaurent.service.CustomerMenuService;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private final CustomerMenuService customerMenuService;

    public CustomerController(CustomerMenuService customerMenuService) {
        this.customerMenuService = customerMenuService;
    }

    @GetMapping
    public List<MenuItem> getMenuForCustomer() {
        return customerMenuService.getMenuForCustomer();
    }
}
