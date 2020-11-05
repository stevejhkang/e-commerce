package com.example.shop.config.security.user;

import com.example.shop.domain.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class UserAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler
    implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException {
        User user = (User) authentication.getDetails();

//        UserSession userSession = new UserSession();
//        userSession.setUser(user);
//        userSession.setLogin(true);
//
//        HttpSession session = httpServletRequest.getSession();
//        session.setMaxInactiveInterval(3600);
//        session.setAttribute("userSession",userSession);

        this.handle(httpServletRequest,httpServletResponse, user.getUserType().name());
        this.clearAuthenticationAttributes(httpServletRequest);
    }

    protected void handle(HttpServletRequest request, HttpServletResponse response, String userType) throws IOException {
        String targetUrl="";
        if(userType.equals("ROLE_BUYER")) {
            targetUrl= "/store?page=1";

        }
        else if(userType.equals("ROLE_SELLER")) {
            targetUrl= "/admin/item/list/1";
        }
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }
}
