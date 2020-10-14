package com.example.shop.repository;

import com.example.shop.dao.delivery.DeliveryDao;
import com.example.shop.dao.delivery.DeliveryDto;
import com.example.shop.domain.delivery.Delivery;
import com.example.shop.domain.delivery.DeliveryRepository;
import com.example.shop.domain.user.User;
import com.example.shop.domain.user.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DeliveryDBRepository implements DeliveryRepository {
    @Autowired
    private DeliveryDao deliveryDao;


    @Override
    public Delivery findByUserSn(int userSn) {
        DeliveryDto dto = deliveryDao.findByUserSn(userSn);
        return toDelivery(dto);
    }

    public Delivery toDelivery(DeliveryDto deliveryDto) {
        User user = User.builder()
            .userSn(deliveryDto.getUserSn())
            .userId(deliveryDto.getUserId())
            .userName(deliveryDto.getUserName())
            .userType(UserType.valueOf(deliveryDto.getUserType()))
            .password(deliveryDto.getPassword())
            .phoneNumber(deliveryDto.getPhoneNumber())
            .build();

        return Delivery.builder()
            .deliverySn(deliveryDto.getDeliverySn())
            .address(deliveryDto.getAddress())
            .phoneNumber1(deliveryDto.getPhoneNumber1())
            .receiverName(deliveryDto.getReceiverName())
            .user(user)
            .build();
    }
}
