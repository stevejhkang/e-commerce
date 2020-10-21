package com.example.shop.controller.item;

import com.example.shop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("item")
public class ItemViewController {
    @Autowired
    ItemService itemService;

    @GetMapping("/detail")
    public String product(@RequestParam("itemNo") int itemNo, Model model) {
        model.addAttribute("item", itemService.findItem(itemNo));
        return "/item/detail";
    }
}
