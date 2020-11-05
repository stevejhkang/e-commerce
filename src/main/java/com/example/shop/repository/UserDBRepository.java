package com.example.shop.repository;

import com.example.shop.dao.user.UserDao;
import com.example.shop.dao.user.UserDto;
import com.example.shop.domain.user.User;
import com.example.shop.domain.user.UserRepository;
import com.example.shop.domain.user.UserType;
import com.example.shop.exception.RestError;
import com.example.shop.exception.RestException;
import com.example.shop.exception.UserAuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDBRepository implements UserRepository {

    @Autowired
    private UserDao userDao;

    public int createUser(User user) {
        return userDao.createUser(UserDto.of(user));
    }

    public User findUserByIdAndPassword(User user) {
        UserDto dto = userDao.findUserByIdAndPassword(UserDto.of(user));
        return toUser(dto);
    }

    @Override
    public User findByUserSn(int userSn) {
        return toUser(userDao.findUserByUserSn(userSn));
    }

    @Override
    public User findByUserId(String userId) {

        return toUser(userDao.findUserByUserId(userId));
    }

    @Override
    public User findByName(String name) {
        return toUser(userDao.findUserByName(name));
    }

    public User toUser(UserDto userDto) {

        UserDto dto = Optional.ofNullable(userDto).orElseThrow(() -> new UserAuthenticationException(RestError.NO_SUCH_USER));

        return User.builder()
                   .userSn(dto.getUserSn())
                   .userId(dto.getUserId())
                   .password(dto.getPassword())
                   .name(dto.getName())
                   .phoneNumber(dto.getPhoneNumber())
                   .deliveryList(dto.getDeliveryList())
                   .orderList(dto.getOrderList())
                   .userType(UserType.valueOf(dto.getUserType()))
                   .build();
    }
}
