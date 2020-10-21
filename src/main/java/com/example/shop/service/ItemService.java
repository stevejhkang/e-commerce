package com.example.shop.service;

import com.example.shop.controller.item.rqrs.CreateItemRq;
import com.example.shop.domain.item.Item;
import com.example.shop.domain.item.ItemRepository;
import com.example.shop.util.FileUtils;
import com.example.shop.util.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ItemService {

    private static final int SUCCESS=1;

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

    public int findTotalCount(){
        return itemRepository.findTotalCount();
    }

    public List<Item> findAllItems(Paging paging){
        int totalCount = findTotalCount();
        paging.setTotalCount(totalCount);
        return itemRepository.findAllItems(paging);
    }

    public Item findItem(int itemSn) {
        return itemRepository.findItem(itemSn);
    }

    public String updateItem(Item item, MultipartFile mainImg) {
        String fileName= itemRepository.findItem(item.getItemSn()).getImgSrc();
        FileUtils.deleteFile(fileName);

        String filePath = FileUtils.uploadToLocalStorage(mainImg);
        item.setImgSrc(filePath);

        int result = itemRepository.updateItem(item);
        if(result==SUCCESS){
            return "success";
        }
        else{
            return "failure";
        }
    }

    public String deleteItem(int itemSn) {
        String fileName= itemRepository.findItem(itemSn).getImgSrc();
        FileUtils.deleteFile(fileName);

        int result= itemRepository.deleteItem(itemSn);
        if(result==SUCCESS){
            return "success";
        }
        else{
            return "failure";
        }
    }
}
