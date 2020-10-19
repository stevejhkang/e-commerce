package com.example.shop.dao.orderitem;

import lombok.Data;

@Data
public class OrderItemDto {
    private int orderitemSn;
    private int orderSn;
    private int itemSn;
    private int quantity;
}
