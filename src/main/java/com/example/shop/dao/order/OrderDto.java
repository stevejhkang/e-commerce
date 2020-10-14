package com.example.shop.dao.order;

import com.example.shop.domain.delivery.Delivery;
import com.example.shop.domain.value.status.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDto {
    private int orderSn;
    private String orderId;
    private LocalDateTime orderDate;
    private String orderStatus;
    private Delivery delivery;
}
