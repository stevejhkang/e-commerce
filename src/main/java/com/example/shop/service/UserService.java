package com.example.shop.service;

import com.example.shop.controller.user.rqrs.CreateUserRq;
import com.example.shop.domain.User;
import com.example.shop.exception.RestError;
import com.example.shop.exception.RestException;
import com.example.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public int createUser(CreateUserRq rq){
        checkConfirmPassword(rq.getPassword(),rq.getConfirmPassword());

        User user = User.builder()
                        .userId(rq.getUserId())
                        .password(rq.getPassword())
                        .userName(rq.getUserName())
                        .phoneNumber(rq.getPhoneNumber())
            .build();

        return userRepository.createUser(user);
    }

    public void checkConfirmPassword(String password, String confirmPassword){
        if(!password.equals(confirmPassword)){
            throw new RestException(RestError.USER_NOT_EQUAL_CONFIRM_PASSWORD);
        }
    }



}
