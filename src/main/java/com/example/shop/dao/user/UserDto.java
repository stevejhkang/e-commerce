package com.example.shop.dao.user;

import com.example.shop.domain.Delivery;
import com.example.shop.domain.Order;
import com.example.shop.domain.User;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.List;

@Data
public class UserDto {
    private int userSn;
    private String userId;
    private String password;
    private String userName;
    private String phoneNumber;
    private List<Order> orderList;
    private List<Delivery> deliveryList;

    public static ModelMapper modelMapper = new ModelMapper();

    public static UserDto of(User user) {
        return modelMapper.map(user,UserDto.class);
    }

}
