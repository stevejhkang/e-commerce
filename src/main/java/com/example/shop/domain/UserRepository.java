package com.example.shop.domain;

import com.example.shop.dao.user.UserDto;

public interface UserRepository {
    public int createUser(User user);
    public User findByIdAndPassword(User user);
    public User toUser(UserDto dto);
}
