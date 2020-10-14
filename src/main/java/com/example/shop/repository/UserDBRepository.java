package com.example.shop.repository;

import com.example.shop.dao.user.UserDao;
import com.example.shop.dao.user.UserDto;
import com.example.shop.domain.user.User;
import com.example.shop.domain.user.UserRepository;
import com.example.shop.domain.user.UserType;
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

    @Override
    public User findByUserSn(int userSn) {
        return toUser(userDao.findByUserSn(userSn));
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
                   .userSn(dto.getUserSn())
                   .userId(dto.getUserId())
                   .password(dto.getPassword())
                   .userName(dto.getUserName())
                   .phoneNumber(dto.getPhoneNumber())
                   .deliveryList(dto.getDeliveryList())
                   .orderList(dto.getOrderList())
                   .userType(UserType.valueOf(dto.getUserType()))
                   .build();
    }
}
