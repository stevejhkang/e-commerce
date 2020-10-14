package com.example.shop.dao.item;

import lombok.Data;

@Data
public class ItemDto {
    private int itemSn;
    private String itemName;
    private int price;
    private String manufacturer;
    private String deliveryOption;
    private String description;
}
