package com.example.shop.repository;

import com.example.shop.dao.item.ItemDao;
import com.example.shop.dao.item.ItemDto;
import com.example.shop.domain.item.Item;
import com.example.shop.domain.item.ItemRepository;
import com.example.shop.domain.item.DeliveryOption;
import com.example.shop.domain.item.DisplayOption;
import com.example.shop.util.PagingSearchAndSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ItemDBRepository implements ItemRepository {
    @Autowired
    private ItemDao itemDao;

    public int createItem(Item item) {
        return itemDao.createItem(ItemDto.of(item));
    }

    @Override
    public int findTotalCount(PagingSearchAndSort pagingSearchAndSort) {
        return itemDao.findTotalCount(pagingSearchAndSort);
    }

    @Override
    public List<Item> findAllItems(PagingSearchAndSort pagingSearchAndSort) {
        List<ItemDto> dtoList = itemDao.findAllItems(pagingSearchAndSort);
        return dtoList.stream().map(this::toItem).collect(Collectors.toList());
    }

    @Override
    public List<Item> findAllDisplayedItems(PagingSearchAndSort pagingSearchAndSort) {
        List<ItemDto> dtoList = itemDao.findAllItems(pagingSearchAndSort);
        return dtoList.stream().map(this::toItem).collect(Collectors.toList());
    }

    @Override
    public Item findItem(int itemSn) {
        return toItem(itemDao.findItem(itemSn));
    }

    @Override
    public int updateItem(Item item) {
        return itemDao.updateItem(ItemDto.of(item));
    }

    @Override
    public int deleteItem(int itemSn) {
        return itemDao.deleteItem(itemSn);
    }

    @Override
    public int updateStocks(List<Item> items) {
        List<ItemDto> itemDtos =items.stream().map(ItemDto::of).collect(Collectors.toList());
        return itemDao.updateStocks(itemDtos);
    }

    private Item toItem(ItemDto dto) {
        return Item.builder()
                   .itemSn(dto.getItemSn())
                   .itemName(dto.getItemName())
                   .description(dto.getDescription())
                   .deliveryOption(DeliveryOption.valueOf(dto.getDeliveryOption()))
                   .displayOption(DisplayOption.valueOf(dto.getDisplayOption()))
                   .imgSrc(dto.getImgSrc())
                   .manufacturer(dto.getManufacturer())
                   .price(dto.getPrice())
                   .stock(dto.getStock())
                   .build();
    }
}

