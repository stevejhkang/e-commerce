package com.example.shop.controller.cart;

import com.example.shop.domain.item.Item;
import com.example.shop.domain.user.User;
import com.example.shop.service.CartService;
import com.example.shop.service.ItemService;
import com.example.shop.session.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import java.util.Map;

@Controller
@RequestMapping("cart")
public class CartViewController {
    @Autowired
    private CartService cartService;
    @Autowired
    private ItemService itemService;

    @GetMapping("/cart")
    public ModelAndView cartItems(){
        ModelAndView modelAndView = new ModelAndView("/cart/cart");

        Map<Item, Integer> items = cartService.getItems();
        modelAndView.addObject("items",items);
        modelAndView.addObject("num_of_items", items.size());
        modelAndView.addObject("total_price",cartService.getTotal());

        return modelAndView;
    }

    @GetMapping("/addItem/{itemSn}/{one}")
    public ModelAndView addItem(@PathVariable("itemSn") int itemSn){
        Item item = itemService.findItem(itemSn);
        cartService.addItem(item,1);

        return cartItems();
    }

    @GetMapping("/deleteItem/{itemSn}")
    public ModelAndView deleteItem(@PathVariable("itemSn") int itemSn){
        Item item = itemService.findItem(itemSn);
        cartService.deleteItem(item);

        return cartItems();
    }

    @GetMapping("/checkout")
    public ModelAndView checkout(HttpServletRequest request) {
        UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");

        cartService.checkOut(userSession.getUser());

        return cartItems();
    }
}
