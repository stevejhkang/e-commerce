package com.example.shop.domain.delivery;


public interface DeliveryRepository {
    public Delivery findByUserSn(int userSn);
}
