package com.finalproject.restaurent.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.finalproject.restaurent.entity.MenuItem;
import com.finalproject.restaurent.exception.ResourceNotFoundException;
import com.finalproject.restaurent.repository.MenuItemRepository;

@Service
public class AdminMenuServiceImpl implements AdminMenuService {
    private final MenuItemRepository menuItemRepository;

    public AdminMenuServiceImpl(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public MenuItem createMenuItem(MenuItem menuItem) {
        menuItem.setActive(true);
        menuItem.setAvailable(true);
        return menuItemRepository.save(menuItem);
    }

    @Override
    public MenuItem updateMenuItem(Long id, MenuItem updatedItem) {
        MenuItem existing = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu item not found"));

        if (updatedItem.getName() != null) {
            existing.setName(updatedItem.getName());
        }
        if (updatedItem.getPrice() != 0) { // assuming price 0 is not valid for update if not provided
            existing.setPrice(updatedItem.getPrice());
        }
        if (updatedItem.getImageUrl() != null) {
            existing.setImageUrl(updatedItem.getImageUrl());
        }

        return menuItemRepository.save(existing);
    }

    @Override
    public void deleteMenuItem(Long id) {
        MenuItem item = menuItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Menu item not found"));
        item.setActive(false);
        menuItemRepository.save(item);
    }

    @Override
    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findByActiveTrue();
    }

}
