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

    @Autowired
    ItemService itemService;

    @GetMapping("/")
    public String hello(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/config")
    public String config() { return "config";}

    @GetMapping("/checkout")
    public String checkout() { return "order/checkout";}

    @GetMapping("/item/detail")
    public String product(@RequestParam("itemNo") int itemNo, Model model) {
        model.addAttribute("item", itemService.findItem(itemNo));
        return "item/detail";
    }

    @GetMapping("/store")
    public String store(@RequestParam("page") int page, Model model) {
        Paging paging = new Paging(page);
        paging.setPageCount(9);

        model.addAttribute("paging",paging);
        model.addAttribute("itemlist",itemService.findAll(paging));

        return "store";
    }

    @GetMapping("/signup")
    public String register() {
        return "user/signup";
    }

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }


}
