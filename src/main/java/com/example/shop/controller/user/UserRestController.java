package com.example.shop.controller.user;

import com.example.shop.controller.user.rqrs.CreateUserRq;
import com.example.shop.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        catch(Exception e){
            e.printStackTrace();
            entity=failureResponse(e);
        }

         return entity;
    }

    public ResponseEntity<String> successResponse(){
        return new ResponseEntity<>("success",HttpStatus.OK);
    }
    public ResponseEntity<String> failureResponse(Exception e){
        e.printStackTrace();
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }
}
