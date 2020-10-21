package com.example.shop.domain.order;

import com.example.shop.util.Paging;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository {
    public String createSequnceNumber();
    public int createOrder(Order order);
    public List<Order> findAllOrdersByUserSn(int userSn, Paging paging);
    public int findTotalCountByUserSn(int userSn);
}
