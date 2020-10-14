package com.example.shop.controller.order;

import com.example.shop.domain.Item.Item;
import com.example.shop.domain.user.User;
import com.example.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order")
public class OrderRestController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<String> createOrder(@SessionAttribute User user, @ModelAttribute Item item, int quantity) {
        ResponseEntity<String> entity = null;
        try{
            int userSn = user.getUserSn();
            int result =orderService.createOrder(user,item,quantity);
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
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
    public ResponseEntity<String> failureResponse(Exception e){
        return new ResponseEntity<>("failure",HttpStatus.BAD_REQUEST);
    }
}
