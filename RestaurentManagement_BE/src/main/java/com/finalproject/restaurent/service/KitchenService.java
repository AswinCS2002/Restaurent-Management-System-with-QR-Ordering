package com.finalproject.restaurent.service;

import java.util.List;

import com.finalproject.restaurent.entity.MenuItem;

public interface KitchenService {
	MenuItem markAvailable(Long menuItemId);

    MenuItem markUnavailable(Long menuItemId);

    List<MenuItem> getActiveMenuItems();

}
