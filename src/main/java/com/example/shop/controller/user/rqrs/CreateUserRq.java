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
    @Size(min = 2, max = 32, message = "2자이상 32자 이하")
    private String userId;
    @NotBlank
    @Size(min = 10, max = 32, message = "10자이상 32자 이하")
    private String password;
    @NotBlank
    @Size(min = 10, max = 32, message = "10자이상 32자 이하")
    private String confirmPassword;
    @NotBlank
    @Size(max = 32, message = "32자 이하")
    private String userName;
    @NotBlank
    @Size(min = 10, max = 11, message = "11자 혹은 12자")
    private String phoneNumber;

}
