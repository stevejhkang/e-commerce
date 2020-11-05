package com.example.shop.dao.order;

import com.example.shop.util.Paging;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OrderDao {
    @Autowired
    private SqlSession sqlSession;

    protected String NAMESPACE = "com.example.shop.OrderMapper.";

    public String createSequenceNumber() {
        return sqlSession.selectOne(NAMESPACE + "createSequenceNumber");
    }

    public int createOrder(OrderDto orderDto) {
        int result = sqlSession.insert(NAMESPACE + "createOrder", orderDto);
        return orderDto.getOrderSn();
    }

    public List<OrderDto> findAllOrdersByUserSn(int userSn, Paging paging) {
        Map<String, Object> map = new HashMap();
        map.put("userSn", userSn);
        map.put("paging", paging);

        return sqlSession.selectList(NAMESPACE + "findAllOrdersByUserSn", map);
    }

    public int findTotalCountByUserSn(int userSn) {
        return sqlSession.selectOne(NAMESPACE + "findTotalCount", userSn);
    }

    public OrderDto findOrderByOrderSn(int orderSn) {
        return sqlSession.selectOne(NAMESPACE + "findOrderByOrderSn", orderSn);
    }

    public int confirmOrder(int orderSn) {
        return sqlSession.update(NAMESPACE + "confirmOrder", orderSn);
    }
}
