package com.example.shop.dao.item;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemDao {
    @Autowired
    private SqlSession sqlSession;

    protected static final String NAMESPACE = "com.example.shop.ItemMapper.";

    public int createItem(ItemDto dto){
        return sqlSession.insert(NAMESPACE+"insert",dto);
    }

}
