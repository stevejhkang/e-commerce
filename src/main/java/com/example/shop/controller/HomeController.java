package com.example.shop.controller;

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
}
