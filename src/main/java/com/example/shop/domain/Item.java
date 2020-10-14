package com.example.shop.domain;

import com.example.shop.domain.status.DeliveryOption;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Item {
    private int itemSn;
    private String itemName;
    private int price;
    private String manufacturer;
    private DeliveryOption deliveryOption;
    private String description;
}
