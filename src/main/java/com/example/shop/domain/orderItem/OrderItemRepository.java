package com.example.shop.domain.orderItem;

import com.example.shop.domain.item.Item;
import com.example.shop.domain.order.Order;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;


public interface OrderItemRepository {
    public int createOrderItems(Set<Map.Entry<Item, Integer>> items, int orderSn) ;

    public List<OrderItem> findOrderItemListByOrderSn(int orderSn);
}
