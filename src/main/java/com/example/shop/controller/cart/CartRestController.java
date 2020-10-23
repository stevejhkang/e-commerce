package com.example.shop.controller.cart;

import com.example.shop.domain.item.Item;
import com.example.shop.service.CartService;
import com.example.shop.service.ItemService;
import com.example.shop.util.ResponseEntityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("cart")
public class CartRestController {
    @Autowired
    private CartService cartService;
    @Autowired
    private ItemService itemService;

    @GetMapping("/addItemAction/{itemSn}")
    public ResponseEntity addItem(@PathVariable("itemSn") int itemSn, @RequestParam("qty") int qty){
        Item item = itemService.findItem(itemSn);
        cartService.addItem(item,qty);

        return ResponseEntityUtil.successResponse();
    }


}
