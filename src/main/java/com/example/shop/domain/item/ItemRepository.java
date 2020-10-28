package com.example.shop.domain.item;

import com.example.shop.util.PagingSearchAndSort;

import java.util.List;

public interface ItemRepository {
    public int createItem(Item item);

    public int findTotalCount(PagingSearchAndSort pagingSearchAndSort);

    public List<Item> findAllItems(PagingSearchAndSort pagingSearchAndSort);

    public List<Item> findAllDisplayedItems(PagingSearchAndSort pagingSearchAndSort);

    public Item findItem(int itemSn);

    public int updateItem(Item item);

    public int deleteItem(int itemSn);

    public int updateStocks(List<Item> keyset);
}
