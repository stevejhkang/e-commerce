package com.example.shop.dao.item;

import com.example.shop.domain.Item.Item;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class ItemDto {
    private int itemSn;
    private String itemName;
    private int price;
    private String deliveryOption;
    private String displayOption;
    private int stock;
    private String manufacturer;
    private String description;
    private String imgSrc;

    public static ModelMapper modelMapper = new ModelMapper();

    public static ItemDto of(Item item) { return modelMapper.map(item, ItemDto.class);}

}
