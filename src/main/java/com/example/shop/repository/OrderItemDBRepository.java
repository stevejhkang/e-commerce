package com.example.shop.repository;

import com.example.shop.dao.orderitem.OrderItemDao;
import com.example.shop.dao.item.ItemDto;
import com.example.shop.domain.item.Item;
import com.example.shop.domain.orderItem.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Repository
public class OrderItemDBRepository implements OrderItemRepository {
    @Autowired
    OrderItemDao orderItemDao;
    @Override
    public int createOrderItems(Set<Map.Entry<Item, Integer>> items, int orderSn) {
        Map<Integer, Integer> itemSnAndQuantity = new HashMap<>();
        for(Map.Entry<Item,Integer> item : items) {
            int itemSn = item.getKey().getItemSn();
            itemSnAndQuantity.put(itemSn, item.getValue());
        }
        return orderItemDao.createOrderItems(itemSnAndQuantity.entrySet(), orderSn);
    }
}
