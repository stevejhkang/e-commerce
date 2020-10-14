package com.example.shop.domain.order;

import com.example.shop.domain.delivery.Delivery;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class Order {
        private int orderSn;
        private String orderId;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Delivery delivery;
}
