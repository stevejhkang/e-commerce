package com.example.shop.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class User {
    Long userSn;
    String userId;
    String password;
    String userName;
    String phoneNumber;
    List<Order> orderList;
    List<Delivery> deliveryList;
}
