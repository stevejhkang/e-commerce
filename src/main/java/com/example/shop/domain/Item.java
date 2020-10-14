package com.example.shop.domain;

import com.example.shop.domain.option.DeliveryOption;
import com.example.shop.domain.option.DisplayOption;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
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
