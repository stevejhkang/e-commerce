package com.example.shop.domain.order;

import com.example.shop.domain.delivery.Delivery;
import com.example.shop.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
        private int orderSn;
        private String orderId;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private BigDecimal price;
        private int userSn;
        private int deliverySn;
        private Delivery delivery;

}
