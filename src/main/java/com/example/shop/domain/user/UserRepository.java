package com.example.shop.domain.user;

import com.example.shop.dao.user.UserDto;

public interface UserRepository {
    public int createUser(User user);
    public User findUserByIdAndPassword(User user);
    public User findByUserSn(int userSn);
    public User toUser(UserDto dto);
}
