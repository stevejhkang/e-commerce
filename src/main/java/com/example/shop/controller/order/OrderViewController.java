package com.example.shop.controller.order;

import com.example.shop.domain.delivery.Delivery;
import com.example.shop.domain.item.Item;
import com.example.shop.domain.order.Order;
import com.example.shop.domain.orderItem.OrderItem;
import com.example.shop.domain.user.User;
import com.example.shop.service.DeliveryService;
import com.example.shop.service.OrderService;
import com.example.shop.session.UserSession;
import com.example.shop.util.Paging;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("order")
@Slf4j
public class OrderViewController {
    static final int NUMBER_OF_ITEMS = 20;
    @Autowired
    private OrderService orderService;
    @Autowired
    private DeliveryService deliveryService;

    @GetMapping("/myorders")
    public String myOrders(@RequestParam("page") int page, Model model, Authentication authentication) {

        User user = (User) authentication.getDetails();
        log.info("logined user: "+user.getUsername());

        Paging paging = new Paging();
        paging.setPageIndex(page);
        paging.setPageCount(NUMBER_OF_ITEMS);

        List<Order> orders =  orderService.findAllOrdersByUserSn(user.getUserSn(),paging);

        model.addAttribute("paging", paging);
        model.addAttribute("orders", orders);

        return "/user/myorders";
    }

    @GetMapping("/ordersuccess")
    public String orderSuccess(){
        return "order/ordersuccess";
    }

    @GetMapping("/checkout")
    public String checkout() { return "order/checkout";}

    @GetMapping("/orderdetail/{orderId}")
    public String orderdetail(@PathVariable("orderId") int orderId, Model model){

        Order order = orderService.findOrderByOrderSn(orderId);

        int userSn = order.getUserSn();

        //배송정보
        Delivery delivery = deliveryService.findDeliveryByUserSn(userSn);
        //총액
        BigDecimal price = order.getPrice();
        //아이템 리스트
        List<OrderItem> orderItemList = orderService.findOrderItemListByOrderSn(orderId);

        model.addAttribute("order",order);
        model.addAttribute("delivery",delivery);
        model.addAttribute("price",price);
        model.addAttribute("orderItemList",orderItemList);

        return "order/orderdetail";
    }
    @GetMapping("/orderconfirm")
    public String orderconfirm(@RequestParam("orderId") String orderId, Model model, Authentication authentication){
        int result = orderService.confirmOrder(Integer.parseInt(orderId));

        User user = (User) authentication.getDetails();
        log.info("logined user: "+user.getUsername());

        Paging paging = new Paging();
        paging.setPageIndex(1);
        paging.setPageCount(NUMBER_OF_ITEMS);

        List<Order> orders =  orderService.findAllOrdersByUserSn(user.getUserSn(),paging);

        model.addAttribute("paging", paging);
        model.addAttribute("orders", orders);

        return "/user/myorders";
    }
}
