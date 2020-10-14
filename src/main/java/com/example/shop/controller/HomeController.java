package com.example.shop.controller;

import com.example.shop.controller.item.rqrs.CreateItemRq;
import com.example.shop.domain.status.DeliveryOption;
import com.example.shop.domain.status.DisplayOption;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/home")
public class HomeController {
    @GetMapping("/hello")
    public String hello(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/product")
    public String product() {
        return "product";
    }

    @GetMapping("/store")
    public String store() {
        return "store";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/item/register")
    public String register_product(Model model){
        CreateItemRq rq = new CreateItemRq();
        rq.setDeliveryOption(DeliveryOption.ShippingCostNotInclude);
        rq.setDisplayOption(DisplayOption.NONE);
        model.addAttribute("createItemRq",rq);
        return "admin/product/register";
    }
}
