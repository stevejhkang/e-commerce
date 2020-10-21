package com.example.shop.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserViewController {
    @GetMapping("/signup")
    public String register() {
        return "user/signup";
    }

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }
}
