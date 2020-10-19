package com.example.shop.config.security.user;

import com.example.shop.domain.user.User;
import com.example.shop.service.UserService;
import com.example.shop.util.ApplicationContextUtil;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private static UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String id = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        User user = findUser(id,password);

        List<GrantedAuthority> grantedAuthorityList = Lists.newArrayList();

        String auth = user.getUserType().name();

        grantedAuthorityList.add(new SimpleGrantedAuthority(auth));
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(id, password, grantedAuthorityList);
        token.setDetails(user);

        return token;
    }

    private User findUser(String id, String password) {
        if(userService==null) {
            userService= ApplicationContextUtil.getBean(UserService.class);
        }
        User user = userService.findByIdAndPassword(id, password);
        if(user==null){
            throw new BadCredentialsException("아이디 혹은 비밀번호가 틀렸습니다.");
        }
        return user;
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
