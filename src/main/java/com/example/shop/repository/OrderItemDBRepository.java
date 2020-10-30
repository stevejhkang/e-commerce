package com.example.shop.repository;

import com.example.shop.dao.item.ItemDao;
import com.example.shop.dao.orderitem.OrderItemDao;
import com.example.shop.dao.item.ItemDto;
import com.example.shop.dao.orderitem.OrderItemDto;
import com.example.shop.domain.item.Item;
import com.example.shop.domain.orderItem.OrderItem;
import com.example.shop.domain.orderItem.OrderItemRepository;
import com.example.shop.exception.RestError;
import com.example.shop.exception.RestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class OrderItemDBRepository implements OrderItemRepository {

    @Autowired
    OrderItemDao orderItemDao;

    @Autowired
    ItemDao itemDao;

    @Override
    public int createOrderItems(Set<Map.Entry<Item, Integer>> items, int orderSn) {
        Map<Integer, Integer> itemSnAndQuantity = new HashMap<>();
        for(Map.Entry<Item,Integer> item : items) {
            int itemSn = item.getKey().getItemSn();
            itemSnAndQuantity.put(itemSn, item.getValue());
        }
        return orderItemDao.createOrderItems(itemSnAndQuantity.entrySet(), orderSn);
    }

    @Override
    public List<OrderItem> findOrderItemListByOrderSn(int orderSn) {
        //여기서 orderitemDto 리스트를 받아오고
        List<OrderItemDto> dtos = orderItemDao.findOrderItemListByOrderSn(orderSn);

        //또 그것을 바탕으로 item리스트를 받아오고 합쳐야된다.
       return dtos.stream().map(this::toOrderItem).collect(Collectors.toList());
    }


    private OrderItem toOrderItem(OrderItemDto dto) {

        ItemDto itemDto = Optional.ofNullable(itemDao.findItemByItemSn(dto.getItemSn()))
                                  .orElseThrow(() -> new RestException(RestError.NO_SUCH_ITEM));

        return OrderItem.builder()
                        .orderitemSn(dto.getOrderitemSn())
                        .itemSn(dto.getItemSn())
                        .itemName(itemDto.getItemName())
                        .price(itemDto.getPrice())
                        .deliveryOption(itemDto.getDeliveryOption())
                        .manufacturer(itemDto.getManufacturer())
                        .description(itemDto.getDescription())
                        .imgSrc(itemDto.getImgSrc())
                        .quantity(dto.getQuantity()).build();
    }

}
