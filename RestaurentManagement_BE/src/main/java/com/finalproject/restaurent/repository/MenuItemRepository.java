package com.finalproject.restaurent.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finalproject.restaurent.entity.MenuItem;

public interface MenuItemRepository extends JpaRepository<MenuItem,Long>{


	List<MenuItem> findByActiveTrueAndAvailableTrue();
	List<MenuItem> findByActiveTrue();

}
