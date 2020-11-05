package com.example.shop.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
