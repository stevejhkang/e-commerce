package com.example.shop.controller.user;

import com.example.shop.controller.user.rqrs.CreateUserRq;
import com.example.shop.controller.user.rqrs.LoginUserRq;
import com.example.shop.domain.User;
import com.example.shop.exception.RestException;
import com.example.shop.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(path="/user")
public class UserRestController {

    @Autowired
    UserService userService;

    @PostMapping("/createUser")
    public ResponseEntity<String> createUser(@ModelAttribute CreateUserRq rq){
        ResponseEntity<String> entity = null;
        try{
            int result = userService.createUser(rq);
            entity=successResponse();
        }
        catch(RuntimeException e){
            e.printStackTrace();
            e.getMessage();
            entity=failureResponse(e);
        }

        return entity;
    }

    @PostMapping("/loginAction")
    public ResponseEntity<String> login(@ModelAttribute LoginUserRq rq){
        ResponseEntity<String> entity = null;
        try{
            User user = userService.findByIdAndPassword(rq);
            entity=successResponse();
        }
        catch(RuntimeException e){
            e.printStackTrace();
            e.getMessage();
            entity=failureResponse(e);
        }

        return entity;
    }

    public ResponseEntity<String> successResponse(){
        return new ResponseEntity<>("success",HttpStatus.OK);
    }
    public ResponseEntity<String> failureResponse(RuntimeException e){
        return new ResponseEntity<>("failure",HttpStatus.BAD_REQUEST);
    }
}
