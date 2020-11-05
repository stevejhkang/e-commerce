package com.example.shop.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.Map;

public class ResponseEntityUtil {
    public static ResponseEntity<String> successResponse() {
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    public static ResponseEntity<String> successResponse(String url) { return new ResponseEntity<>(url, HttpStatus.OK);}

    public static ResponseEntity<String> failureResponse() {
        return new ResponseEntity<>("failure", HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<String> failureResponse(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity redirectResponse(String newURL) {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(newURL));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    public static ResponseEntity successResponse(Map map) {
        return new ResponseEntity(map, HttpStatus.OK);
    }
}
