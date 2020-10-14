package com.example.shop.domain;

import com.example.shop.domain.status.DeliveryOption;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Item {
    Long itemSn;
    String itemName;
    int price;
    String manufacturer;
    DeliveryOption deliveryOption;
    String description;
}
