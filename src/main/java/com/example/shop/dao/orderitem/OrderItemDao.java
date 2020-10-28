package com.example.shop.dao.orderitem;

import com.example.shop.domain.item.Item;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class OrderItemDao {
    @Autowired
    private SqlSession sqlSession;

    protected static final String NAMESPACE = "com.example.shop.OrderItemMapper.";
    public int createOrderItems(Set<Map.Entry<Integer, Integer>> items, int orderSn){
        Map<String, Object> map = new HashMap<>();
        map.put("items",items);
        map.put("orderSn",orderSn);
        return sqlSession.insert(NAMESPACE+"createOrderItems",map);
    }

    public List<OrderItemDto> findOrderItemListByOrderSn(int orderSn) {
        return sqlSession.selectList(NAMESPACE+"findOrderItemListByOrderSn",orderSn);
    }
}
