package com.example.shop.dao.delivery;

import com.example.shop.domain.delivery.Delivery;
import com.example.shop.domain.user.User;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class DeliveryDto {
    private int deliverySn;
    private String receiver;
    private String address;
    private String phoneNumber1;
    private User user;

    public static ModelMapper modelMapper = new ModelMapper();

    public static DeliveryDto of(Delivery delivery) { return modelMapper.map(delivery, DeliveryDto.class);}
}
