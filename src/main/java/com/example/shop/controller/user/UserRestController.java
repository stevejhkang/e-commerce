package com.example.shop.controller.user;

import com.example.shop.controller.user.rqrs.CreateUserRq;
import com.example.shop.controller.user.rqrs.LoginUserRq;
import com.example.shop.domain.user.User;
import com.example.shop.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping(path="/user")
public class UserRestController {

    @Autowired
    UserService userService;

    @PostMapping("/createUser")
    public ResponseEntity<String> createUser(@ModelAttribute CreateUserRq rq,HttpServletResponse response){
        ResponseEntity<String> entity = null;
        try{
            int result = userService.createUser(rq);
            entity=successResponse();
            response.sendRedirect("/home/store");
        }
        catch(Exception e){
            e.printStackTrace();
            e.getMessage();
            entity=failureResponse(e);
        }

         return entity;
    }

    @PostMapping("/loginAction")
    public ResponseEntity<String> login(HttpServletRequest request, @ModelAttribute LoginUserRq rq, HttpServletResponse response){
        ResponseEntity<String> entity = null;
        try{
            User user = userService.findByIdAndPassword(rq.getUserId(),rq.getPassword());
            HttpSession session = (HttpSession) request.getSession();
            session.setAttribute("islogined",true);
            entity=successResponse();
            response.sendRedirect("/home/store");
        }
        catch(RuntimeException | IOException e){
            e.printStackTrace();
            e.getMessage();
            entity=failureResponse(e);
        }
        return entity;
    }

    @GetMapping("/logoutAction")
    public ResponseEntity<String> logout(HttpServletRequest request, @ModelAttribute LoginUserRq rq, HttpServletResponse response) {
        ResponseEntity<String> entity= null;

        HttpSession session = (HttpSession) request.getSession();
        session.invalidate();

        entity= successResponse();

        try {
            response.sendRedirect("/home/store");
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return entity;
    }

    public ResponseEntity<String> successResponse(){
        return new ResponseEntity<>("success",HttpStatus.OK);
    }
    public ResponseEntity<String> failureResponse(Exception e){
        return new ResponseEntity<>("failure",HttpStatus.BAD_REQUEST);
    }
}
