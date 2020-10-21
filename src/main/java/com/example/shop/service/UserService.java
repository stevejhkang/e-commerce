package com.example.shop.service;

import com.example.shop.controller.user.rqrs.CreateUserRq;
import com.example.shop.controller.user.rqrs.LoginUserRq;
import com.example.shop.domain.user.User;
import com.example.shop.domain.user.UserRepository;
import com.example.shop.exception.RestError;
import com.example.shop.exception.RestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private static final int SUCCESS=1;

    public String createUser(CreateUserRq rq){
        checkConfirmPassword(rq.getPassword(),rq.getConfirmPassword());

        User user = User.builder()
                        .userId(rq.getUserId())
                        .password(rq.getPassword())
                        .userName(rq.getUserName())
                        .phoneNumber(rq.getPhoneNumber())
            .build();

        int result = userRepository.createUser(user);
        if(result == SUCCESS){
            return "success";
        }
        else {
            return "failure";
        }
    }

    public void checkConfirmPassword(String password, String confirmPassword){
        if(!password.equals(confirmPassword)){
            throw new RestException(RestError.USER_NOT_EQUAL_CONFIRM_PASSWORD);
        }
    }

    public User findUserByIdAndPassword(String id, String password){
        User user = User.builder()
                        .userId(id)
                        .password(password)
                        .build();

        Optional<User> result = Optional.ofNullable(userRepository.findUserByIdAndPassword(user));

        return result.orElseThrow(() -> new RestException(RestError.CANNOT_FIND_ACCOUNT));
    }

    public User findByUserSn(int userSn) {
        return userRepository.findByUserSn(userSn);
    }
}
