package com.example.shop.controller.user.rqrs;

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
