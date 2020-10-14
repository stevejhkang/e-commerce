package com.example.shop.domain.order;

import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository {
    public String createSequnceNumber();
    public int createOrder(Order order);
}
