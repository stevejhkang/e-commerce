package com.example.shop.controller.order;

import com.example.shop.controller.order.rqrs.CreateOrderRq;
import com.example.shop.domain.item.Item;
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

    @PostMapping("/createOrder")
    public ResponseEntity<String> createOrder(@RequestBody CreateOrderRq data) {
        //@SessionAttribute User user,
        //, int quantity
        ResponseEntity<String> entity = null;
        Item item = data.getItem();
        int quantity = data.getQuantity();
        try{
//            int userSn = user.getUserSn();
//            int result =orderService.createOrder(user,item,quantity);
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
