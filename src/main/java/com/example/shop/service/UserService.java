package com.example.shop.service;

import com.example.shop.controller.user.rqrs.CreateUserRq;
import com.example.shop.controller.user.rqrs.LoginUserRq;
import com.example.shop.domain.user.User;
import com.example.shop.domain.user.UserRepository;
import com.example.shop.exception.RestError;
import com.example.shop.exception.RestException;
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

    public User findByIdAndPassword(LoginUserRq rq){
        User user = User.builder()
                        .userId(rq.getUserId())
                        .password(rq.getPassword())
                        .build();
        User result = userRepository.findByIdAndPassword(user);
        if(result==null){
            throw new RestException(RestError.CANNOT_FIND_ACCOUNT);
        }
        return result;
    }



}
