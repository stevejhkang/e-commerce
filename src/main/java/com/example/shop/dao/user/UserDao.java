package com.example.shop.dao.user;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDao {
    @Autowired
    private SqlSession sqlSession;

    protected static final String NAMESPACE = "com.example.shop.UserMapper.";

    public int createUser(UserDto dto) {
        return sqlSession.insert(NAMESPACE+"createUser",dto);
    }


    public UserDto findUserByIdAndPassword(UserDto dto) {
        return sqlSession.selectOne(NAMESPACE+"findUserByIdAndPassword",dto);
    }

    public UserDto findUserByUserSn(int userSn) {
        return sqlSession.selectOne(NAMESPACE+"findUserByUserSn",userSn);
    }
}
