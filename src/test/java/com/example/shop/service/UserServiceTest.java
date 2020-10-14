package com.example.shop.service;

import com.example.shop.controller.user.rqrs.CreateUserRq;
import com.example.shop.domain.User;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml","file:src/main/webapp/WEB-INF/dispatcher-servlet.xml",
    "file:src/main/resources/database/mybatis-config.xml"})
@WebAppConfiguration
public class UserServiceTest extends TestCase {
    @Autowired
    UserService userService;

//    @Autowired
//    private WebApplicationContext wac;
//
//    private MockMvc mockMvc;
//
//    @Before
//    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this);
//        mockMvc= MockMvcBuilders.standaloneSetup()
//    }

    @Test
    public void createTest(){
        CreateUserRq rq = new CreateUserRq();
        rq.setUserId("tes123t");
        rq.setPassword("asd123f");
        rq.setUserName("asdfa123sdf");
        rq.setPhoneNumber("123123123123");
        int result =userService.createUser(rq);
        assertEquals(1,result);
    }
}