package com.example.shop.service;

import com.example.shop.controller.order.rqrs.createOrderOneItemRq;
import com.example.shop.domain.item.Item;
import com.example.shop.domain.delivery.Delivery;
import com.example.shop.domain.delivery.DeliveryRepository;
import com.example.shop.domain.order.Order;
import com.example.shop.domain.order.OrderRepository;
import com.example.shop.domain.order.OrderStatus;
import com.example.shop.domain.orderItem.OrderItemRepository;
import com.example.shop.domain.user.User;
import com.example.shop.util.CommonUtils;
import com.example.shop.util.Paging;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Slf4j
public class OrderService {
    private static final int SUCCESS=1;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private DeliveryRepository deliveryRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    private static final int INSERT_NOTHING =0;

    public String createOrder(User user, createOrderOneItemRq data) {
        Delivery delivery = getDeliveryInfoByUserSn(user.getUserSn());

        Item item = data.getItem();
        int quantity = data.getQuantity();

        LocalDateTime now = LocalDateTime.now();

        Order order = Order.builder()
                           .orderId(createOrderId(now))
                           .orderDate(now)
                           .orderStatus(OrderStatus.PAYMENT_COMPLETED)
                           .price((BigDecimal.valueOf(item.getPrice())).multiply(BigDecimal.valueOf(quantity)))
                           .delivery(delivery)
                           .user(user).build();

        int result = orderRepository.createOrder(order);
        if (result == SUCCESS) {
            return "success";
        }
        else {
            return "failure";
        }
    }

    public String createOrderId(LocalDateTime now) {
        String uuid = CommonUtils.getRandomString();
        String orderId = ('s'+now.toString()+uuid).replaceAll("(\\.|-|T|:)","");

        return orderId;
    }

    public Delivery getDeliveryInfoByUserSn(int userSn){
        return deliveryRepository.findByUserSn(userSn);
    }

    public String createOrder(User user, Set<Map.Entry<Item,Integer>> items, BigDecimal totalPrice) {
         Delivery delivery = getDeliveryInfoByUserSn(user.getUserSn());

        LocalDateTime now = LocalDateTime.now();

        Order order = Order.builder()
                           .orderId(createOrderId(now))
                           .orderDate(now)
                           .orderStatus(OrderStatus.PAYMENT_COMPLETED)
                           .price(totalPrice)
                           .delivery(delivery)
                           .user(user).build();

        int returnOrderSn = orderRepository.createOrder(order);
        log.info(String.valueOf(returnOrderSn));

        int orderitemsCreateResult = orderItemRepository.createOrderItems(items,returnOrderSn);

        log.info(String.valueOf(orderitemsCreateResult));

        if(orderitemsCreateResult==INSERT_NOTHING){
            return "failure";
        } else {
            return "success";
        }
    }

    public List<Order> findAllOrdersByUserSn(int userSn, Paging paging) {
        int totalCount = findTotalCountByUserSn(userSn);
        paging.setTotalCount(totalCount);
        return orderRepository.findAllOrdersByUserSn(userSn, paging);
    }
    public int findTotalCountByUserSn(int userSn){
        return orderRepository.findTotalCountByUserSn(userSn);
    }
}
