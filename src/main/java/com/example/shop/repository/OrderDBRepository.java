package com.example.shop.repository;

import com.example.shop.dao.delivery.DeliveryDao;
import com.example.shop.dao.delivery.DeliveryDto;
import com.example.shop.dao.order.OrderDao;
import com.example.shop.dao.order.OrderDto;
import com.example.shop.dao.user.UserDao;
import com.example.shop.domain.delivery.Delivery;
import com.example.shop.domain.order.Order;
import com.example.shop.domain.order.OrderRepository;
import com.example.shop.domain.order.OrderStatus;
import com.example.shop.util.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class OrderDBRepository implements OrderRepository {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private DeliveryDao deliveryDao;
    @Autowired
    private UserDao userDao;

    @Override
    public String createSequnceNumber() {
        return orderDao.createSequenceNumber();
    }

    @Override
    public int createOrder(Order order) {
        return orderDao.createOrder(OrderDto.of(order));
    }

    @Override
    public List<Order> findAllOrdersByUserSn(int userSn, Paging paging) {
        List<OrderDto> dtoList = orderDao.findAllOrdersByUserSn(userSn, paging);
        return dtoList.stream().map(this::toOrder).collect(Collectors.toList());
    }

    @Override
    public int findTotalCountByUserSn(int userSn) {
        return orderDao.findTotalCountByUserSn(userSn);
    }

    @Override
    public Order findOrderByOrderSn(int orderSn) {
        OrderDto dto = orderDao.findOrderByOrderSn(orderSn);
        return toOrder(dto);
    }

    @Override
    public int confirmOrder(int orderSn) {
        return orderDao.confirmOrder(orderSn);
    }

    private Order toOrder(OrderDto orderDto){

        DeliveryDto deliveryDto = deliveryDao.findDeliveryByUserSn(orderDto.getUserSn());
        Delivery delivery = Delivery.builder()
                                    .receiverName(deliveryDto.getReceiverName())
                                    .phoneNumber1(deliveryDto.getPhoneNumber1())
                                    .address(deliveryDto.getAddress())
                                    .deliverySn(deliveryDto.getDeliverySn()).build();

        Order order = Order.builder()
                           .orderSn(orderDto.getOrderSn())
                           .orderId(orderDto.getOrderId())
                           .orderDate(orderDto.getOrderDate())
                           .orderStatus(OrderStatus.valueOf(orderDto.getOrderStatus()))
                           .price(orderDto.getPrice())
                           .userSn(orderDto.getUserSn())
                           .deliverySn(orderDto.getDeliverySn())
                           .delivery(delivery)
                           .build();
        return order;
    }
}
