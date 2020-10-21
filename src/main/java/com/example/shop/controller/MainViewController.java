package com.example.shop.controller;

import com.example.shop.service.ItemService;
import com.example.shop.util.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class MainViewController {
    static final int NUMBER_OF_ITEMS = 9;

    @Autowired
    ItemService itemService;

    @GetMapping("/")
    public String hello(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }

    @GetMapping("/store")
    public String store(@RequestParam("page") int page, Model model) {
        Paging paging = new Paging();
        paging.setPageIndex(page);
        paging.setPageCount(NUMBER_OF_ITEMS);

        model.addAttribute("paging",paging);
        model.addAttribute("itemlist",itemService.findAllItems(paging));

        return "store";
    }
}
