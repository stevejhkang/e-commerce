package com.example.shop.service;

import com.example.shop.domain.item.Item;
import com.example.shop.domain.delivery.Delivery;
import com.example.shop.domain.delivery.DeliveryRepository;
import com.example.shop.domain.order.Order;
import com.example.shop.domain.order.OrderRepository;
import com.example.shop.domain.order.OrderStatus;
import com.example.shop.domain.user.User;
import com.example.shop.util.CommonUtils;
import com.example.shop.util.Paging;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private static final int SUCCESS=1;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private DeliveryRepository deliveryRepository;

    public String createOrder(User user, Item item, int quantity) {
        Delivery delivery = deliveryRepository.findByUserSn(user.getUserSn());

        LocalDateTime now = new LocalDateTime();
        String uuid = CommonUtils.getRandomString();
        String orderId = ('s'+now.toString()+uuid).replaceAll("(\\.|-|T|:)","");

        Order order = new Order();
        order.setOrderId(orderId);
        order.setDelivery(delivery);
        order.setUser(user);
        order.setOrderStatus(OrderStatus.PAYMENT_COMPLETED);
        order.setPrice(item.getPrice()*quantity);

        int result = orderRepository.createOrder(order);
        if(result==SUCCESS){
            return "success";
        }
        else {
            return "failure";
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
