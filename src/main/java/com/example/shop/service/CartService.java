package com.example.shop.service;

import com.example.shop.domain.item.Item;
import com.example.shop.domain.item.ItemRepository;
import com.example.shop.domain.user.User;
import com.example.shop.exception.RestError;
import com.example.shop.exception.RestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class CartService {
    private Map<Item, Integer> items = new HashMap<>();

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderService orderService;

    public void addItemToCart(Item item, int qty) {
        if (items.containsKey(item)) {
            items.replace(item, items.get(item) + qty);
        }
        else {
            items.put(item, qty);
        }
    }

    public void deleteItemInCart(Item item) {
        if (items.containsKey(item)) {
            items.remove(item);
        }
    }

    public void changeQuantity(Item item, int quantity) {
        items.put(item, quantity);
    }

    public Map<Item, Integer> getItems() {
        return Collections.unmodifiableMap(items);
    }

    @Transactional
    public void checkOut(User user) {
        Item item;
        for (Map.Entry<Item, Integer> entry : items.entrySet()) {
            item = itemRepository.findItem(entry.getKey().getItemSn());
            if (item.getStock() < entry.getValue()) {
                throw new RestException(RestError.NOT_ENOUGH_ITEM);
            }
            entry.getKey().setStock(item.getStock() - entry.getValue());
        }

        itemRepository.updateStocks(new ArrayList<>(items.keySet()));

        orderService.createOrders(user, items.entrySet(), getTotal());

        items.clear();
    }

    public BigDecimal getTotal() {
        BigDecimal total_price = items.entrySet().stream()
                                      .map(entry -> BigDecimal.valueOf(entry.getKey().getPrice())
                                                              .multiply(BigDecimal.valueOf(entry.getValue())))
                                      .reduce(BigDecimal::add)
                                      .orElse(BigDecimal.ZERO);
        return total_price;
    }
}
