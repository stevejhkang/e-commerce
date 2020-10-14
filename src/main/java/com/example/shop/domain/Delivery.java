package com.example.shop.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Delivery {
    Long deliverSn;
    String receiver;
    String address;
    String phoneNumber1;
    User user;
}
