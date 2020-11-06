package com.example.shop.controller.user.rqrs;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
                getterVisibility = JsonAutoDetect.Visibility.NONE,
                isGetterVisibility = JsonAutoDetect.Visibility.NONE)

@Data
@ToString
public class CreateUserRq {
    @NotBlank
    @Size(min = 2, max = 32, message = "Number of user ID letters must be from 2 to 32")
    private String userId;
    @NotBlank
    @Size(min = 5, max = 32, message = "Number of user password letters must be from 5 to 32")
    private String password;
    @NotBlank
    @Size(min = 5, max = 32, message = "Number of user password letters must be from 5 to 32")
    private String confirmPassword;
    @NotBlank
    @Size(max = 32, message = "Number of user name letters must be below 32")
    private String userName;
    @NotBlank
    @Size(min = 10, max = 11, message = "Number of phone number must be 11 or 12")
    private String phoneNumber;

}
