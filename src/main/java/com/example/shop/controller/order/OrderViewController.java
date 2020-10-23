package com.example.shop.controller.order;

import com.example.shop.domain.order.Order;
import com.example.shop.service.OrderService;
import com.example.shop.session.UserSession;
import com.example.shop.util.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("order")
public class OrderViewController {
    static final int NUMBER_OF_ITEMS = 20;
    @Autowired
    OrderService orderService;

    @GetMapping("/myorders")
    public String myOrders(@RequestParam("page") int page, HttpServletRequest request, Model model){

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

        return "/user/mypage";
    }

    @GetMapping("/ordersuccess")
    public String orderSuccess(){
        return "order/ordersuccess";
    }

    @GetMapping("/checkout")
    public String checkout() { return "order/checkout";}
}
