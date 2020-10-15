package com.example.shop.domain.item;

import com.example.shop.util.Paging;

import java.util.List;

public interface ItemRepository {
    public int createItem(Item item);
    public int findTotalCount();
    public List<Item> findAll(Paging paging);

    public Item findItem(int itemSn);

    public int updateItem(Item item);

    public int deleteItem(int itemSn);
}
