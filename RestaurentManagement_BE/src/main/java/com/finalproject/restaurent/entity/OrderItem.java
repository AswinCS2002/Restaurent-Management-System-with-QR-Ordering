package com.finalproject.restaurent.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "order_id")
	@JsonIgnore
	private Order order;

	@ManyToOne(optional = false)
	@JoinColumn(name = "menu_item_id")
	private MenuItem menuItem;

	@Column(nullable = false)
	private int quantity;

	@Column(name = "price_at_order", nullable = false)
	private int priceAtOrder;

	public OrderItem() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public MenuItem getMenuItem() {
		return menuItem;
	}

	public void setMenuItem(MenuItem menuItem) {
		this.menuItem = menuItem;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPriceAtOrder() {
		return priceAtOrder;
	}

	public void setPriceAtOrder(int priceAtOrder) {
		this.priceAtOrder = priceAtOrder;
	}

}
