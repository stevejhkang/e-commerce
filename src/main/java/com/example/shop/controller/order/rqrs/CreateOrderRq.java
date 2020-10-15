package com.example.shop.controller.order.rqrs;

import com.example.shop.domain.item.Item;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CreateOrderRq {
    Item item;
    int quantity;
}
