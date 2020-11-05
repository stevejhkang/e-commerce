package com.example.shop.controller.item;

import com.example.shop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("item")
public class ItemViewController {
    @Autowired
    private ItemService itemService;

    @GetMapping("/detail/{itemNo}")
    public String readItem(@PathVariable("itemNo") int itemNo, Model model) {

        model.addAttribute("item", itemService.readItem(itemNo));
        return "/item/itemdetail";
    }
}
