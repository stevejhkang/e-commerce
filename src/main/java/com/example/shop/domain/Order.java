package com.example.shop.domain;

import com.example.shop.domain.status.OrderStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class Order {
    Long orderSn;
    String orderId;
    LocalDateTime orderDate;
    OrderStatus orderStatus;
    Delivery delivery;
}
