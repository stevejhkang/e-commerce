package com.example.shop.dao.order;

import com.example.shop.domain.delivery.Delivery;
import com.example.shop.domain.order.Order;
import com.example.shop.domain.user.User;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@Data
public class OrderDto {
    private int orderSn;
    private String orderId;
    private LocalDateTime orderDate;
    private String orderStatus;
    private int price;
    private Delivery delivery;
    private User user;

    public static ModelMapper modelMapper = new ModelMapper();

    public static OrderDto of(Order order) {
        return modelMapper.map(order, OrderDto.class);
    }
}
