package com.example.shop.dao.order;

import com.example.shop.domain.delivery.Delivery;
import com.example.shop.domain.order.Order;
import com.example.shop.domain.user.User;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderDto {
    private int orderSn;
    private String orderId;
    private LocalDateTime orderDate;
    private String orderStatus;
    private BigDecimal price;

    private int deliverySn;
    private String receiverName;
    private String address;
    private String phoneNumber1;

    private int userSn;
    private String userId;
    private String password;
    private String userName;
    private String phoneNumber;
    private String userType;

    public static ModelMapper modelMapper = new ModelMapper();

    public static OrderDto of(Order order) {
//        return modelMapper.map(order, OrderDto.class);
        OrderDto orderDto = new OrderDto();

        orderDto.setOrderSn(order.getOrderSn());
        orderDto.setOrderId(order.getOrderId());
        orderDto.setOrderDate(order.getOrderDate());
        orderDto.setOrderStatus(order.getOrderStatus().name());
        orderDto.setPrice(order.getPrice());

        orderDto.setUserSn(order.getUserSn());
        orderDto.setDeliverySn(order.getDeliverySn());

        return orderDto;
    }

}
