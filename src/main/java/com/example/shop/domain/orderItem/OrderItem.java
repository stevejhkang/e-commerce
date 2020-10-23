package com.example.shop.domain.orderItem;

import com.example.shop.domain.item.Item;
import com.example.shop.domain.order.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    private int orderitemSn;
    private Order order;
    private Item item;
    private int quantity;
}
