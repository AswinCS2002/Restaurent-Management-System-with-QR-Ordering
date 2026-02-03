package com.finalproject.restaurent.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.finalproject.restaurent.entity.MenuItem;
import com.finalproject.restaurent.repository.MenuItemRepository;

@Service
public class CustomerMenuServiceImpl implements CustomerMenuService {
	private final MenuItemRepository menuItemRepository;

    public CustomerMenuServiceImpl(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public List<MenuItem> getMenuForCustomer() {
        return menuItemRepository.findByActiveTrueAndAvailableTrue();
    }
}
