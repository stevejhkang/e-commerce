package com.example.shop.controller.user;

import com.example.shop.controller.user.rqrs.createUserRq;
import com.example.shop.controller.user.rqrs.loginUserRq;
import com.example.shop.domain.user.User;
import com.example.shop.service.UserService;
import com.example.shop.util.ResponseEntityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping(path="/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @PostMapping("/createUser")
    public ResponseEntity createUser(@ModelAttribute createUserRq rq){
        ResponseEntity<String> entity = null;

        String result = userService.createUser(rq);
        if(result.equals("success")){
            entity= ResponseEntityUtil.successResponse();
        }
        else {
            entity=ResponseEntityUtil.failureResponse();
        }
        return entity;
    }

    @PostMapping("/loginAction")
    public ResponseEntity login(HttpServletRequest request, @ModelAttribute loginUserRq rq){
        ResponseEntity<String> entity = null;

        User user = userService.findUserByIdAndPassword(rq.getUserId(), rq.getPassword());

        HttpSession session = (HttpSession) request.getSession();
        session.setAttribute("islogined",true);

        String url = "/store?pageIndex=1";
        entity=ResponseEntityUtil.redirectResponse(url);

        return entity;
    }

    @GetMapping("/logoutAction")
    public ResponseEntity logout(HttpServletRequest request, @ModelAttribute loginUserRq rq) {
        ResponseEntity<String> entity= null;

        HttpSession session = (HttpSession) request.getSession();
        session.invalidate();

        String url = "/store?pageIndex=1";
        entity = ResponseEntityUtil.redirectResponse(url);

        return entity;
    }
}
