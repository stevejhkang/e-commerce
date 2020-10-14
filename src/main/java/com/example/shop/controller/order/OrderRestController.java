package com.example.shop.controller.order;

import com.example.shop.domain.Item.Item;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
public class OrderRestController {
    @PostMapping
    public ResponseEntity<String> createOrder(@ModelAttribute Item item, int quantity) {
        ResponseEntity<String> entity = null;
        try{

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
