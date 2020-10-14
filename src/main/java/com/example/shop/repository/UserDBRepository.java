package com.example.shop.repository;

import com.example.shop.dao.order.OrderDao;
import com.example.shop.dao.order.OrderDto;
import com.example.shop.dao.user.UserDao;
import com.example.shop.dao.user.UserDto;
import com.example.shop.domain.User;
import com.example.shop.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDBRepository implements UserRepository {
    @Autowired
    private UserDao userDao;

//    @Autowired
//    private OrderDao orderDao;

    public int createUser(User user){
        return userDao.createUser(UserDto.of(user));
    }

    public User findByIdAndPassword(User user) {
        UserDto dto = userDao.findByIdAndPassword(UserDto.of(user));
        return toUser(dto);
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

    public User toUser(UserDto dto){
        return User.builder()
            .userId(dto.getUserId())
            .password(dto.getPassword())
            .userName(dto.getUserName())
            .phoneNumber(dto.getPhoneNumber())
            .deliveryList(dto.getDeliveryList())
            .orderList(dto.getOrderList())
            .build();
    }
}
