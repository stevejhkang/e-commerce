package com.example.shop.controller.cart;

import com.example.shop.domain.item.Item;
import com.example.shop.service.CartService;
import com.example.shop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

        modelAndView.addObject("items",cartService.getItems());
        modelAndView.addObject("total_price",cartService.getTotal());

        return modelAndView;
    }

    @GetMapping("/addItem/{itemSn}")
    public ModelAndView addItem(@PathVariable("itemSn") int itemSn){
        Item item = itemService.findItem(itemSn);
        cartService.addItem(item);

        return cartItems();
    }

    @GetMapping("/deleteItem/{itemSn}")
    public ModelAndView deleteItem(@PathVariable("itemSn") int itemSn){
        Item item = itemService.findItem(itemSn);
        cartService.deleteItem(item);

        return cartItems();
    }

    @GetMapping("/checkout")
    public ModelAndView checkout() {
        cartService.checkOut();

        return cartItems();
    }
}
