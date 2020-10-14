package com.example.shop.dao.delivery;

import com.example.shop.domain.delivery.Delivery;
import com.example.shop.domain.user.User;
import com.example.shop.domain.user.UserType;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class DeliveryDto {
    private int deliverySn;
    private String receiverName;
    private String address;
    private String phoneNumber1;
    private int UserSn;
    private String userId;
    private String password;
    private String userName;
    private String phoneNumber;
    private String userType;

    public static DeliveryDto of(Delivery delivery) {
        DeliveryDto deliveryDto = new DeliveryDto();
        deliveryDto.setDeliverySn(delivery.getDeliverySn());
        deliveryDto.setReceiverName(delivery.getReceiverName());
        deliveryDto.setAddress(delivery.getAddress());
        deliveryDto.setPhoneNumber1(delivery.getPhoneNumber1());

        return deliveryDto;
    }
    public static DeliveryDto of(Delivery delivery, User user) {
        DeliveryDto deliveryDto = new DeliveryDto();
        deliveryDto.setDeliverySn(delivery.getDeliverySn());
        deliveryDto.setReceiverName(delivery.getReceiverName());
        deliveryDto.setAddress(delivery.getAddress());
        deliveryDto.setPhoneNumber1(delivery.getPhoneNumber1());
        deliveryDto.setUserSn(user.getUserSn());
        deliveryDto.setUserId(user.getUserName());
        deliveryDto.setPassword(user.getPassword());
        deliveryDto.setUserName(user.getUserName());
        deliveryDto.setPhoneNumber(user.getPhoneNumber());
        deliveryDto.setUserType(user.getUserType().name());

        return deliveryDto;
    }
}
