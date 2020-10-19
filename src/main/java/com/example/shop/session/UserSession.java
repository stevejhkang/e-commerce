package com.example.shop.session;

import com.example.shop.domain.user.User;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserSession implements Serializable {
    public User user = null;
    public boolean login = false;


}
