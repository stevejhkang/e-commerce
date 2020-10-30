package com.example.shop.domain.delivery;


public interface DeliveryRepository {
    public Delivery findDeliveryByUserSn(int userSn);
}
