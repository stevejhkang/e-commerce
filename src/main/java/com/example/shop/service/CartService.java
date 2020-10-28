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
import java.util.*;

@Service
public class CartService {
    private Map<Item, Integer> items = new HashMap<>();

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderService orderService;

    public void addItem(Item item, int qty) {
        if(items.containsKey(item)){
            items.replace(item,items.get(item)+qty);
        } else {
            items.put(item,qty);
        }
    }

    public void deleteItem(Item item) {
        if(items.containsKey(item)){
            items.remove(item);
        }
    }

    public void changeQuantity(Item item, int quantity){
        items.put(item,quantity);
    }

    public Map<Item, Integer> getItems(){
        return Collections.unmodifiableMap(items);
    }

    @Transactional
    public void checkOut(User user) {
        //모든 아이템들이 현재 구매하려고 하는 개수보다 현재 재고가 더 많은지를 확인해야한다. 적으면 에러 던진다.
        Item item;
        for(Map.Entry<Item, Integer> entry: items.entrySet()) {
            //수량을 체크해서 빼준다.
            item = itemRepository.findItem(entry.getKey().getItemSn());
            if(item.getStock()<entry.getValue()){
                throw new RestException(RestError.NOT_ENOUGH_ITEM);
            }
            entry.getKey().setStock(item.getStock()-entry.getValue());
        }

        //해당 아이템을 업데이트 해준다.
        itemRepository.updateStocks(new ArrayList<>(items.keySet()));

        //order와 orderitem을 기록해준다.
        orderService.createOrder(user, items.entrySet(),getTotal());

        //map을 비워준다.
        items.clear();
    }

    public BigDecimal getTotal() {
        BigDecimal total_price = items.entrySet().stream()
            .map(entry -> BigDecimal.valueOf(entry.getKey().getPrice()).multiply(BigDecimal.valueOf(entry.getValue())))
                 .reduce(BigDecimal::add)
                 .orElse(BigDecimal.ZERO);
        return total_price;
    }
}
