package com.example.shop.service;

import com.example.shop.domain.Item.Item;
import com.example.shop.domain.delivery.Delivery;
import com.example.shop.domain.delivery.DeliveryRepository;
import com.example.shop.domain.order.Order;
import com.example.shop.domain.order.OrderRepository;
import com.example.shop.domain.order.OrderStatus;
import com.example.shop.domain.user.User;
import com.example.shop.util.CommonUtils;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private DeliveryRepository deliveryRepository;
//    @Autowired
//    private


    public int createOrder(User user, Item item, int quantity) {
        Delivery delivery = deliveryRepository.findByUserSn(user.getUserSn());

        //주문번호를 만들어준다. ('S'+날짜+고유 sequence 번호)
        LocalDateTime now = new LocalDateTime();
//        String sequence_number = orderRepository.createSequnceNumber();
        String uuid = CommonUtils.getRandomString();
        String orderId = ('s'+now.toString()+uuid).replaceAll("(\\.|-|T|:)","");

        //Order 객체에 주문번호, 유저, 딜리버리를 셋팅한다.
        Order order = new Order();
        order.setOrderId(orderId);
        order.setDelivery(delivery);
        order.setUser(user);
        order.setOrderStatus(OrderStatus.PAYMENT_COMPLETED);
        order.setPrice(item.getPrice()*quantity);

        //레포를 이용해 넣는다
        int result = orderRepository.createOrder(order);

        //반환한다.
        return result;
    }
}
