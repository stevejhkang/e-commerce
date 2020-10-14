package com.example.shop.config;

import com.example.shop.domain.user.User;
import com.example.shop.service.UserService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public class AdminSecurityConfig implements AuthenticationProvider {

    @Autowired
    private static UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String id = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        User user = userService.findByIdAndPassword(id, password);

        List<GrantedAuthority> grantedAuthorityList = Lists.newArrayList();

        String auth = user.getUserType().name();

        grantedAuthorityList.add(new SimpleGrantedAuthority(auth));
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(id, password, grantedAuthorityList);
        token.setDetails(user);

        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
