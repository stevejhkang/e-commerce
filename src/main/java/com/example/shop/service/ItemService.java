package com.example.shop.service;

import com.example.shop.controller.item.rqrs.CreateItemRq;
import com.example.shop.domain.Item;
import com.example.shop.domain.ItemRepository;
import com.example.shop.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public int createItem(CreateItemRq rq, MultipartFile mainImg){

        String filePath = FileUtils.uploadToLocalStorage(mainImg);

        Item item = Item.builder()
                        .itemName(rq.getItemName())
                        .description(rq.getDescription())
                        .deliveryOption(rq.getDeliveryOption())
                        .displayOption(rq.getDisplayOption())
                        .manufacturer(rq.getManufacturer())
                        .price(rq.getPrice())
                        .stock(rq.getStock())
                        .imgSrc(filePath)
                        .build();


        int result = itemRepository.createItem(item);

        return result;
    }
}
