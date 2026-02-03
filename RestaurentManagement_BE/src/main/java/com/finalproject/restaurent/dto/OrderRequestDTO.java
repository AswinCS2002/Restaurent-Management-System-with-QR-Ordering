package com.finalproject.restaurent.dto;

import java.util.List;

public class OrderRequestDTO {
	private int tableNumber;
    private List<OrderItemRequestDTO> items;

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public List<OrderItemRequestDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemRequestDTO> items) {
        this.items = items;
    }

}
