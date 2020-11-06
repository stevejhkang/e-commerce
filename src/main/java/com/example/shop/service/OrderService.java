package com.example.shop.service;

import com.example.shop.controller.order.rqrs.CreateOrderOneItemRq;
import com.example.shop.domain.delivery.Delivery;
import com.example.shop.domain.delivery.DeliveryRepository;
import com.example.shop.domain.item.Item;
import com.example.shop.domain.order.Order;
import com.example.shop.domain.order.OrderRepository;
import com.example.shop.domain.order.OrderStatus;
import com.example.shop.domain.orderItem.OrderItem;
import com.example.shop.domain.orderItem.OrderItemRepository;
import com.example.shop.domain.user.User;
import com.example.shop.util.CommonUtils;
import com.example.shop.util.Paging;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Slf4j
public class OrderService {
    private static final int SUCCESS = 1;
    private static final int FAILURE = 0;
    private static final int INSERT_NOTHING = 0;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private DeliveryRepository deliveryRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    public String createOrders(User user, CreateOrderOneItemRq data) {
        Delivery delivery = deliveryRepository.findDeliveryByUserSn(user.getUserSn());

        Item item = data.getItem();
        int quantity = data.getQuantity();

        LocalDateTime now = LocalDateTime.now();

        Order order = Order.builder()
                           .orderId(createOrderId(now))
                           .orderDate(now)
                           .orderStatus(OrderStatus.PAYMENT_COMPLETED)
                           .price((BigDecimal.valueOf(item.getPrice())).multiply(BigDecimal.valueOf(quantity)))
                           .userSn(user.getUserSn())
                           .deliverySn(delivery.getDeliverySn())
                           .delivery(delivery)
                           .build();

        int result = orderRepository.createOrder(order);

        if (result == FAILURE) {
            return "failure";
        }
        else {
            return "success";
        }
    }

    public String createOrderId(LocalDateTime now) {
        String uuid = CommonUtils.getRandomString();
        String orderId = ('s' + now.toString() + uuid).replaceAll("(\\.|-|T|:)", "");

        return orderId;
    }

    public String createOrders(User user, Set<Map.Entry<Item, Integer>> items, BigDecimal totalPrice) {
        Delivery delivery = deliveryRepository.findDeliveryByUserSn(user.getUserSn());

        LocalDateTime now = LocalDateTime.now();

        Order order = Order.builder()
                           .orderId(createOrderId(now))
                           .orderDate(now)
                           .orderStatus(OrderStatus.PAYMENT_COMPLETED)
                           .userSn(user.getUserSn())
                           .deliverySn(delivery.getDeliverySn())
                           .price(totalPrice)
                           .build();

        int returnOrderSn = orderRepository.createOrder(order);
        log.info(String.valueOf(returnOrderSn));

        int orderitemsCreateResult = orderItemRepository.createOrderItems(items, returnOrderSn);

        log.info(String.valueOf(orderitemsCreateResult));

        if (orderitemsCreateResult == INSERT_NOTHING) {
            return "failure";
        }
        else {
            return "success";
        }
    }

    public List<Order> readAllOrdersByUserSn(int userSn, Paging paging) {
        int totalCount = readTotalCountByUserSn(userSn);
        paging.setTotalCount(totalCount);

        return orderRepository.findAllOrdersByUserSn(userSn, paging);
    }

    public int readTotalCountByUserSn(int userSn) {
        return orderRepository.findTotalCountByUserSn(userSn);
    }

    public Order readOrderByOrderSn(int orderSn) {
        return orderRepository.findOrderByOrderSn(orderSn);
    }

    public List<OrderItem> readOrderItemListByOrderSn(int orderSn) {
        return orderItemRepository.findOrderItemListByOrderSn(orderSn);
    }

    public int confirmOrder(int orderSn) {
        return orderRepository.confirmOrder(orderSn);
    }
}
