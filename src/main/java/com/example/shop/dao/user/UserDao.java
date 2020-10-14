package com.example.shop.dao.user;

import com.example.shop.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UserDao {
    @Autowired
    private SqlSession sqlSession;

    protected static final String NAMESPACE = "com.example.shop.UserMapper.";

    public int createUser(UserDto dto) {
        return sqlSession.insert(NAMESPACE+"insert",dto);
    }


    public UserDto findByIdAndPassword(UserDto dto) {
        return sqlSession.selectOne(NAMESPACE+"selectByIdAndPassword",dto);
    }
}
