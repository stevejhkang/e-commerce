package com.example.shop.dao.delivery;

import com.example.shop.domain.User;
import lombok.Data;

@Data
public class DeliveryDto {
    private int deliverySn;
    private String receiver;
    private String address;
    private String phoneNumber1;
    private User user;
}
