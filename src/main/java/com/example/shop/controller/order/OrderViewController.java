package com.example.shop.controller.order;

import com.example.shop.domain.delivery.Delivery;
import com.example.shop.domain.item.Item;
import com.example.shop.domain.order.Order;
import com.example.shop.domain.orderItem.OrderItem;
import com.example.shop.service.DeliveryService;
import com.example.shop.service.OrderService;
import com.example.shop.session.UserSession;
import com.example.shop.util.Paging;
import org.springframework.beans.factory.annotation.Autowired;
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
public class OrderViewController {
    static final int NUMBER_OF_ITEMS = 20;
    @Autowired
    OrderService orderService;
    @Autowired
    DeliveryService deliveryService;

    @GetMapping("/myorders")
    public String myOrders(@RequestParam("page") int page, HttpServletRequest request, Model model) {
//        UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
        Optional<UserSession> userSession = Optional.ofNullable((UserSession) request.getSession().getAttribute("userSession"));

        if(!userSession.isPresent()) {
            return "/user/login";
        }

        int userSn = userSession.get().getUser().getUserSn();

        Paging paging = new Paging();
        paging.setPageIndex(page);
        paging.setPageCount(NUMBER_OF_ITEMS);

        List<Order> orders =  orderService.findAllOrdersByUserSn(userSn,paging);

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
    public String orderconfirm(@RequestParam("orderId") String orderId, HttpServletRequest request, Model model){
        int result = orderService.confirmOrder(Integer.parseInt(orderId));

        Optional<UserSession> userSession = Optional.ofNullable((UserSession) request.getSession().getAttribute("userSession"));

        if(!userSession.isPresent()) {
            return "/user/login";
        }

        int userSn = userSession.get().getUser().getUserSn();

        Paging paging = new Paging();
        paging.setPageIndex(1);
        paging.setPageCount(NUMBER_OF_ITEMS);

        List<Order> orders =  orderService.findAllOrdersByUserSn(userSn,paging);

        model.addAttribute("paging", paging);
        model.addAttribute("orders", orders);

        return "/user/myorders";
    }
}
