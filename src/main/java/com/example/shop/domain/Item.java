package com.example.shop.domain;

import com.example.shop.domain.status.DeliveryOption;
import com.example.shop.domain.status.DisplayOption;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Item {
    private int itemSn;
    private String itemName;
    private int price;
    private DeliveryOption deliveryOption;
    private DisplayOption displayOption;
    private int stock;
    private String manufacturer;
    private String description;
    private String imgSrc;
}
