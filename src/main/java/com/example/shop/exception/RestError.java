package com.example.shop.exception;

import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum RestError {
    NO_SUCH_USER("01-00"),
    USER_NOT_EQUAL_CONFIRM_PASSWORD("01-01"),

    NO_SUCH_ITEM("02-00"),
    NOT_ENOUGH_ITEM("02-01"),

    NO_SUCH_DELIVERY("03-00"),

    NO_SUCH_ORDER("04-00");



    private final HttpStatus status;
    private final String code;

    RestError(String code){
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        this.code=code;
    }
    public static void validate() {
        int uniqueErrorCodesSize = Arrays.stream(RestError.values())
                                         .map(RestError::getCode)
                                         .collect(Collectors.toSet())
                                         .size();

        if (uniqueErrorCodesSize != RestError.values().length) {
            throw new RuntimeException("RestError code값은 중복될 수 없습니다!");
        }
    }
   public String getCode(){return code;}
   public HttpStatus getStatus() { return status;}
}
