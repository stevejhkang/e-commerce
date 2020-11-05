package com.example.shop.service;

import com.example.shop.domain.delivery.Delivery;
import com.example.shop.domain.delivery.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {
    @Autowired
    DeliveryRepository deliveryRepository;

    public Delivery readDeliveryByUserSn(int userSn) {
        return deliveryRepository.findDeliveryByUserSn(userSn);
    }
}
