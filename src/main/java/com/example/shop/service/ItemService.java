package com.example.shop.service;

import com.example.shop.controller.item.rqrs.CreateItemRq;
import com.example.shop.domain.item.Item;
import com.example.shop.domain.item.ItemRepository;
import com.example.shop.exception.RestError;
import com.example.shop.exception.RestException;
import com.example.shop.util.FileUtils;
import com.example.shop.util.PagingSearchAndSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private static final int SUCCESS = 1;

    @Autowired
    private ItemRepository itemRepository;

    public String createItem(CreateItemRq rq, MultipartFile mainImg) {

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

        return returnMessage(result);
    }

    public int readTotalCount(PagingSearchAndSort pagingSearchAndSort) {
        return itemRepository.findTotalCount(pagingSearchAndSort);
    }

    public List<Item> readAllItems(PagingSearchAndSort pagingSearchAndSort) {
        int totalCount = readTotalCount(pagingSearchAndSort);
        pagingSearchAndSort.setTotalCount(totalCount);
        return itemRepository.findAllItems(pagingSearchAndSort);
    }

    public List<Item> readAllDisplayedItems(PagingSearchAndSort pagingSearchAndSort) {
        int totalCount = readTotalCount(pagingSearchAndSort);
        pagingSearchAndSort.setTotalCount(totalCount);
        return itemRepository.findAllDisplayedItems(pagingSearchAndSort);
    }

    public Item readItem(int itemSn) {
        Optional<Item> item = Optional.ofNullable(itemRepository.findItem(itemSn));

        return item.orElseThrow(() -> new RestException(RestError.NO_SUCH_ITEM));
    }

    public String updateItem(Item item, MultipartFile mainImg) {
        String fileName = itemRepository.findItem(item.getItemSn()).getImgSrc();
        FileUtils.deleteFile(fileName);

        String filePath = FileUtils.uploadToLocalStorage(mainImg);
        item.setImgSrc(filePath);

        int result = itemRepository.updateItem(item);

        return returnMessage(result);
    }

    public String deleteItem(int itemSn) {
        String fileName = itemRepository.findItem(itemSn).getImgSrc();
        FileUtils.deleteFile(fileName);

        int result = itemRepository.deleteItem(itemSn);

        return returnMessage(result);
    }

    public String returnMessage(int result) {
        if (result == SUCCESS) {
            return "success";
        }
        else {
            return "failure";
        }
    }
}
