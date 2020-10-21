package com.example.shop.repository;

import com.example.shop.dao.delivery.DeliveryDao;
import com.example.shop.dao.order.OrderDao;
import com.example.shop.dao.order.OrderDto;
import com.example.shop.domain.delivery.Delivery;
import com.example.shop.domain.order.Order;
import com.example.shop.domain.order.OrderRepository;
import com.example.shop.domain.order.OrderStatus;
import com.example.shop.domain.user.User;
import com.example.shop.domain.user.UserType;
import com.example.shop.util.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class OrderDBRepository implements OrderRepository {
    @Autowired
    private OrderDao orderDao;

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
        return dtoList.stream().map(OrderDBRepository::toOrder).collect(Collectors.toList());
    }

    @Override
    public int findTotalCountByUserSn(int userSn) {
        return orderDao.findTotalCountByUserSn(userSn);
    }

    public static Order toOrder(OrderDto orderDto){
        Delivery delivery = Delivery.builder()
                                    .deliverySn(orderDto.getDeliverySn())
                                    .receiverName(orderDto.getReceiverName())
                                    .address(orderDto.getAddress())
                                    .phoneNumber1(orderDto.getPhoneNumber1())
                                    .build();
        User user = User.builder()
                        .userSn(orderDto.getUserSn())
                        .userId(orderDto.getUserId())
                        .password(orderDto.getPassword())
                        .phoneNumber(orderDto.getPhoneNumber())
                        .userType(UserType.valueOf(orderDto.getUserType())).build();

        Order order = Order.builder()
                            .orderSn(orderDto.getOrderSn())
                            .orderId(orderDto.getOrderId())
                            .orderDate(orderDto.getOrderDate())
                            .orderStatus(OrderStatus.valueOf(orderDto.getOrderStatus()))
                            .price(orderDto.getPrice())
                            .delivery(delivery)
                            .user(user)
                            .build();
        return order;
    }
}
