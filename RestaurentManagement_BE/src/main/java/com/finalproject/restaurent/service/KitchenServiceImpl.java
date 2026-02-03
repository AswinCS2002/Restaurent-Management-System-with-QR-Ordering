package com.finalproject.restaurent.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.finalproject.restaurent.entity.MenuItem;
import com.finalproject.restaurent.exception.ResourceNotFoundException;
import com.finalproject.restaurent.repository.MenuItemRepository;

@Service
public class KitchenServiceImpl implements KitchenService{
	private final MenuItemRepository menuItemRepository;

    public KitchenServiceImpl(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public MenuItem markAvailable(Long menuItemId) {
        MenuItem item = menuItemRepository.findById(menuItemId)
        		.orElseThrow(() -> new ResourceNotFoundException("Menu item not found"));

        item.setAvailable(true);
        return menuItemRepository.save(item);
    }

    @Override
    public MenuItem markUnavailable(Long menuItemId) {
        MenuItem item = menuItemRepository.findById(menuItemId)
        		.orElseThrow(() -> new ResourceNotFoundException("Menu item not found"));

        item.setAvailable(false);
        return menuItemRepository.save(item);
    }

    @Override
    public List<MenuItem> getActiveMenuItems() {
        return menuItemRepository.findByActiveTrue();
    }
}
