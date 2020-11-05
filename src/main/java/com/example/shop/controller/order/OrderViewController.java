package com.example.shop.controller.order;

import com.example.shop.domain.delivery.Delivery;
import com.example.shop.domain.order.Order;
import com.example.shop.domain.orderItem.OrderItem;
import com.example.shop.domain.user.User;
import com.example.shop.service.DeliveryService;
import com.example.shop.service.OrderService;
import com.example.shop.util.Paging;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

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
    public String readAllOrders(@RequestParam("page") int page, Model model, Authentication authentication) {

        User user = (User) authentication.getDetails();
        log.info("logined user: " + user.getUsername());

        Paging paging = new Paging();
        paging.setPageIndex(page);
        paging.setPageCount(NUMBER_OF_ITEMS);

        List<Order> orders = orderService.readAllOrdersByUserSn(user.getUserSn(), paging);

        model.addAttribute("paging", paging);
        model.addAttribute("orders", orders);

        return "/user/myorders";
    }

    @GetMapping("/ordersuccess")
    public String orderSuccess() {
        return "order/ordersuccess";
    }

    @GetMapping("/checkout")
    public String checkout() { return "order/checkout";}

    @GetMapping("/orderdetail/{orderId}")
    public String orderdetail(@PathVariable("orderId") int orderId, Model model) {

        Order order = orderService.readOrderByOrderSn(orderId);

        int userSn = order.getUserSn();


        Delivery delivery = deliveryService.readDeliveryByUserSn(userSn);

        BigDecimal price = order.getPrice();

        List<OrderItem> orderItemList = orderService.readOrderItemListByOrderSn(orderId);

        model.addAttribute("order", order);
        model.addAttribute("delivery", delivery);
        model.addAttribute("price", price);
        model.addAttribute("orderItemList", orderItemList);

        return "order/orderdetail";
    }

    @GetMapping("/orderconfirm")
    public String confirmOrder(@RequestParam("orderId") String orderId, Model model, Authentication authentication) {
        int result = orderService.confirmOrder(Integer.parseInt(orderId));

        User user = (User) authentication.getDetails();
        log.info("logined user: " + user.getUsername());

        Paging paging = new Paging();
        paging.setPageIndex(1);
        paging.setPageCount(NUMBER_OF_ITEMS);

        List<Order> orders = orderService.readAllOrdersByUserSn(user.getUserSn(), paging);

        model.addAttribute("paging", paging);
        model.addAttribute("orders", orders);

        return "/user/myorders";
    }
}
