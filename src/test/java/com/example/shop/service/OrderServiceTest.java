package com.example.shop.service;

import com.example.shop.domain.item.Item;
import com.example.shop.domain.delivery.DeliveryRepository;
import com.example.shop.domain.user.User;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml","file:src/main/webapp/WEB-INF/dispatcher-servlet.xml",
    "file:src/main/resources/database/mybatis-config.xml"})
@WebAppConfiguration
public class OrderServiceTest extends TestCase {
    @Autowired
    private OrderService orderService;
    @Autowired
    private DeliveryRepository deliveryRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ItemService itemService;

//    @Test
//    public void createTest(){
//        //User, Item, quantity
//        User user = userService.findByUserSn(1);
//        Item item = itemService.findItem(1);
//        int quantity = 3;
//
////        String result = orderService.createOrder(user,item,quantity);
//
//        assertEquals("success",result);
//    }
}