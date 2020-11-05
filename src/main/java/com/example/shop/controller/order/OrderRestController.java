package com.example.shop.controller.order;

import com.example.shop.controller.order.rqrs.CreateOrderOneItemRq;
import com.example.shop.domain.user.User;
import com.example.shop.service.OrderService;
import com.example.shop.util.ResponseEntityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
@Slf4j
public class OrderRestController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/createOrder")
    public ResponseEntity createOrder(@RequestBody CreateOrderOneItemRq data, Authentication authentication) {

        ResponseEntity entity = null;

        User user = (User) authentication.getDetails();
        log.info("logined user: " + user.getUsername());

        String result = orderService.createOrders(user, data);

        if (result.equals("success")) {
            String url = "/order/ordersuccess";
            entity = ResponseEntityUtil.successResponse(url);
        }
        else {
            entity = ResponseEntityUtil.failureResponse();
        }
        return entity;
    }
}
