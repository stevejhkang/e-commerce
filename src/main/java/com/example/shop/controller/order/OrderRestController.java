package com.example.shop.controller.order;

import com.example.shop.controller.order.rqrs.CreateOrderRq;
import com.example.shop.domain.item.Item;
import com.example.shop.domain.user.User;
import com.example.shop.service.OrderService;
import com.example.shop.session.UserSession;
import com.example.shop.util.ResponseEntityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import static com.example.shop.util.ResponseEntityUtil.failureResponse;

@RestController
@RequestMapping("order")
@Slf4j
public class OrderRestController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/createOrder")
    public ResponseEntity createOrder(HttpServletRequest request, HttpServletResponse response, @RequestBody CreateOrderRq data) {

        ResponseEntity entity = null;

        HttpSession session = request.getSession();
        User user = ((UserSession) session.getAttribute("userSession")).getUser();

        String result = orderService.createOrder(user,data);

        if(result.equals("success")) {
            String url = "/order/ordersuccess";
            entity= ResponseEntityUtil.successResponse(url);
        }
        else {
            entity=ResponseEntityUtil.failureResponse();
        }
        return entity;
    }


}
