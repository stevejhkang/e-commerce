package com.example.shop.controller.order.rqrs;

import com.example.shop.domain.Item.Item;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CreateOrderRq {
    Item item;

}
