package com.example.shop.repository;

import com.example.shop.dao.user.UserDao;
import com.example.shop.dao.user.UserDto;
import com.example.shop.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    @Autowired
    private UserDao userDao;

//    @Autowired
//    private OrderDao orderDao;

    public int createUser(User user){
        return userDao.createUser(UserDto.of(user));
    }

//    public User findUser(String id ) {
//         UserDto userDto = userDao.find(id);
//         OrderDto orderDto = orderDao.find(userDto.getUserId());
//         return toUser(userDto, orderDto);
//    }
//
//    private User toUser(UserDto userDto, OrderDto orderDto) {
//        return new User(userDto.get)
//    }
}
