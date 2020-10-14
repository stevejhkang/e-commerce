package com.example.shop.domain.delivery;

import com.example.shop.domain.user.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Delivery {
    private int deliverySn;
    private String receiverName;
    private String address;
    private String phoneNumber1;
    private User user;
}
