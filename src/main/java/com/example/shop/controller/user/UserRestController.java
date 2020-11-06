package com.example.shop.controller.user;

import com.example.shop.controller.user.rqrs.CreateUserRq;
import com.example.shop.controller.user.rqrs.LoginUserRq;
import com.example.shop.service.UserService;
import com.example.shop.util.ResponseEntityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(path = "/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @PostMapping("/createUser")
    public ResponseEntity createUser(@ModelAttribute @Valid CreateUserRq rq) {
        ResponseEntity<String> entity = null;

        String result = userService.createUser(rq);

        if (result.equals("success")) {
            entity = ResponseEntityUtil.successResponse();
        }
        else {
            entity = ResponseEntityUtil.failureResponse();
        }
        return entity;
    }

    @GetMapping("/logoutAction")
    public ResponseEntity logout(HttpServletRequest request, @ModelAttribute LoginUserRq rq, Authentication authentication) {
        ResponseEntity<String> entity = null;

        if (authentication != null && authentication.getDetails() != null) {
            try {
                request.getSession().invalidate();
            }
            catch (Exception e) {
                log.error(e.getMessage());
            }
        }

        String url = "/store?pageIndex=1";
        entity = ResponseEntityUtil.redirectResponse(url);

        return entity;
    }
}
