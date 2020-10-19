package com.example.shop.controller.order;

import com.example.shop.domain.order.Order;
import com.example.shop.service.OrderService;
import com.example.shop.session.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("order")
public class OrderViewController {
    @Autowired
    OrderService orderService;

    @GetMapping("/myorders")
    public String myOrders(HttpServletRequest request, Model model){

        UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
        int userSn = userSession.getUser().getUserSn();
        List<Order> orders =  orderService.findAllOrdersByUserSn(userSn);

        model.addAttribute("orders",orders);

        return "/user/mypage";
    }
}
