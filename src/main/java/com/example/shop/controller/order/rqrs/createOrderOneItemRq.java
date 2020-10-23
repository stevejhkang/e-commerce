package com.example.shop.controller.order.rqrs;

import com.example.shop.domain.item.Item;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.ToString;


@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
                getterVisibility = JsonAutoDetect.Visibility.NONE,
                isGetterVisibility = JsonAutoDetect.Visibility.NONE)

@Data
@ToString
public class createOrderOneItemRq {
    Item item;
    int quantity;
}
