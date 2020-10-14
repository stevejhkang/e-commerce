package com.example.shop.dao.order;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderDao {
    @Autowired
    private SqlSession sqlSession;

    protected String NAMESPACE = "com.example.shop.OrderMapper.";

    public String createSequenceNumber() {
        return sqlSession.selectOne(NAMESPACE+"selectSequenceNumber");
    }

    public int createOrder(OrderDto orderDto) {
        return sqlSession.insert(NAMESPACE+"insert", orderDto);
    }
}
