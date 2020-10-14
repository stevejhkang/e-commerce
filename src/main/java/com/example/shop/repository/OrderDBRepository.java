package com.example.shop.repository;

import com.example.shop.dao.order.OrderDao;
import com.example.shop.dao.order.OrderDto;
import com.example.shop.domain.order.Order;
import com.example.shop.domain.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDBRepository implements OrderRepository {
    @Autowired
    private OrderDao orderDao;

    @Override
    public String createSequnceNumber() {
        return orderDao.createSequenceNumber();
    }

    @Override
    public int createOrder(Order order) {
        return orderDao.createOrder(OrderDto.of(order));
    }
}
