package com.example.shop.repository;

import com.example.shop.dao.delivery.DeliveryDao;
import com.example.shop.dao.delivery.DeliveryDto;
import com.example.shop.domain.delivery.Delivery;
import com.example.shop.domain.delivery.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DeliveryDBRepository implements DeliveryRepository {
    @Autowired
    private DeliveryDao deliveryDao;


    @Override
    public Delivery findByUserSn(int userSn) {
        return toDelivery(deliveryDao.findByUserSn(userSn));
    }

    public Delivery toDelivery(DeliveryDto deliveryDto) {
        return Delivery.builder()
            .deliverySn(deliveryDto.getDeliverySn())
            .address(deliveryDto.getAddress())
            .phoneNumber1(deliveryDto.getPhoneNumber1())
            .receiver(deliveryDto.getReceiver())
            .user(deliveryDto.getUser())
            .build();
    }
}
