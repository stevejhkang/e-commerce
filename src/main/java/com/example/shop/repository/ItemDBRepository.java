package com.example.shop.repository;

import com.example.shop.dao.item.ItemDao;
import com.example.shop.dao.item.ItemDto;
import com.example.shop.domain.Item;
import com.example.shop.domain.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ItemDBRepository implements ItemRepository {
    @Autowired
    private ItemDao itemDao;

    public int createItem(Item item){
        return itemDao.createItem(ItemDto.of(item));
    }
}
