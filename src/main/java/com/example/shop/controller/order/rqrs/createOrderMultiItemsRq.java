package com.example.shop.controller.order.rqrs;

import com.example.shop.domain.item.DeliveryOption;
import com.example.shop.domain.item.DisplayOption;
import com.example.shop.domain.item.Item;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.ToString;


@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
                getterVisibility = JsonAutoDetect.Visibility.NONE,
                isGetterVisibility = JsonAutoDetect.Visibility.NONE)

@Data
@ToString
public class createOrderMultiItemsRq {
    private int itemSn;
    private String itemName;
    private int price;
    private DeliveryOption deliveryOption;
    private DisplayOption displayOption;
    private int stock;
    private String manufacturer;
    private String description;
    private String imgSrc;
    int quantity;
}
