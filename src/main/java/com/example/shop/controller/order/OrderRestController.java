package com.example.shop.controller.order;

import com.example.shop.controller.order.rqrs.CreateOrderRq;
import com.example.shop.domain.item.Item;
import com.example.shop.domain.user.User;
import com.example.shop.service.OrderService;
import com.example.shop.session.UserSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("order")
@Slf4j
public class OrderRestController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/createOrder")
    public String createOrder(HttpServletRequest request, HttpServletResponse response, @RequestBody CreateOrderRq data) {
        //@SessionAttribute User user,
        //, int quantity
        ResponseEntity<String> entity = null;
        Item item = data.getItem();
        int quantity = data.getQuantity();
        HttpSession session = request.getSession();
        User user = ((UserSession) session.getAttribute("userSession")).getUser();
        int result =orderService.createOrder(user,item,quantity);
        try{
//            int userSn = user.getUserSn();
//            int result =orderService.createOrder(user,item,quantity);
            entity=successResponse();

//            HttpHeaders headers = new HttpHeaders();
//            headers.setLocation(URI.create("/ordersuccess"));
            return "/ordersuccess";
        }
        catch(Exception e){
            log.error(e.getMessage());
            entity=failureResponse(e);
            return "failure";

        }
    }


    public ResponseEntity<String> successResponse(){
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    public ResponseEntity<String> failureResponse(Exception e){
        return new ResponseEntity<>("failure",HttpStatus.BAD_REQUEST);
    }
}
