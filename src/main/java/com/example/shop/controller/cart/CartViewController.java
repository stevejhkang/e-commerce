package com.example.shop.controller.cart;

import com.example.shop.domain.item.Item;
import com.example.shop.domain.user.User;
import com.example.shop.service.CartService;
import com.example.shop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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

    @GetMapping("/mycart")
    public ModelAndView readCartItems() {

        ModelAndView modelAndView = new ModelAndView("/cart/cart");

        Map<Item, Integer> items = cartService.getItems();
        modelAndView.addObject("items", items);
        modelAndView.addObject("num_of_items", items.size());
        modelAndView.addObject("total_price", cartService.getTotal());

        return modelAndView;
    }

    @GetMapping("/addItem/{itemSn}")
    public ModelAndView addItemToCart(@PathVariable("itemSn") int itemSn) {

        Item item = itemService.readItem(itemSn);
        cartService.addItemToCart(item, 1);

        return readCartItems();
    }

    @GetMapping("/deleteItem/{itemSn}")
    public ModelAndView deleteItemToCart(@PathVariable("itemSn") int itemSn) {

        Item item = itemService.readItem(itemSn);
        cartService.deleteItemInCart(item);

        return readCartItems();
    }

    @GetMapping("/checkout")
    public ModelAndView checkout(Authentication authentication) {

        User user = (User) authentication.getDetails();

        cartService.checkOut(user);

        return readCartItems();
    }
}
