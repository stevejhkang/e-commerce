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
import java.util.Optional;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String id = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        User user = findUser(id);

        checkMatchPassword(id, password,user.getPassword());

        List<GrantedAuthority> grantedAuthorityList = Lists.newArrayList();

        String auth = user.getUserType().name();
        grantedAuthorityList.add(new SimpleGrantedAuthority(auth));
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(id, password, grantedAuthorityList);

        token.setDetails(user);

        return token;
    }

    public User findUser(String id) {
//        if(userService==null) {
//            userService= ApplicationContextUtil.getBean(UserService.class);
//        }
        Optional<User> user = Optional.ofNullable(userService.loadUserByUsername(id));

        return user.orElseThrow(() -> new BadCredentialsException(id));
    }

    public boolean checkMatchPassword(String id, String inputPassword, String password){
        if(!inputPassword.equals(password)) {
            throw new BadCredentialsException(id);
        }
        return inputPassword.equals(password);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
