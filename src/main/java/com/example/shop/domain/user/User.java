package com.example.shop.domain.user;

import com.example.shop.domain.delivery.Delivery;
import com.example.shop.domain.order.Order;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class User {
    private int userSn;
    private String userId;
    private String password;
    private String userName;
    private String phoneNumber;
    private List<Order> orderList;
    private List<Delivery> deliveryList;
}
