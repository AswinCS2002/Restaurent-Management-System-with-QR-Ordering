package com.finalproject.restaurent.service;

import java.util.List;

import com.finalproject.restaurent.entity.MenuItem;

public interface AdminMenuService {
    MenuItem createMenuItem(MenuItem menuItem);

    MenuItem updateMenuItem(Long id, MenuItem menuItem);

    void deleteMenuItem(Long id);

    List<MenuItem> getAllMenuItems();
}
