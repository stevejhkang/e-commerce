package com.example.shop.dao.delivery;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeliveryDao {
    @Autowired
    private SqlSession sqlSession;

    protected static final String NAMESPACE = "com.example.shop.DeliveryMapper.";

    public DeliveryDto findByUserSn(int userSn) {
        return sqlSession.selectOne(NAMESPACE+"selectByUserSn",userSn);
    }

}
