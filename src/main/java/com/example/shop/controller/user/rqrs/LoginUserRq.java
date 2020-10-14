package com.example.shop.controller.user.rqrs;

import com.example.shop.domain.User;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Data
@ToString
public class LoginUserRq {
    @NotBlank
    private String userId;
    @NotBlank
    private String password;
}
