package com.atChenKuan.test;

import com.atChenKuan.pojo.*;
import com.atChenKuan.service.OrderService;
import com.atChenKuan.service.UserService;
import com.atChenKuan.service.impl.OrderServiceImpl;
import com.atChenKuan.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author 陈宽
 * @create 2021-05-09 16:07
 * @description
 */
public class OrderServiceTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void createOrder() {
        Cart cart = new Cart();

        cart.addItem(new CartItem(1,"java从入门到放弃",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(2,"数据结构",1,new BigDecimal(200),new BigDecimal(200)));
        cart.addItem(new CartItem(3,"C语言编程",1,new BigDecimal(250),new BigDecimal(250)));

        OrderService orderService = new OrderServiceImpl();
        System.out.println("生成的订单号是:" + orderService.createOrder(cart,1));

    }

    @Test
    public void showMyOrders(){
        OrderService orderService = new OrderServiceImpl();
        UserService userService = new UserServiceImpl();
        List<Order> ckOrder = orderService.showMyOrders(userService.login(new User("ck", "123456", "370488625@qq.com")));
        System.out.println(ckOrder);
    }

    @Test
    public void showOrderDetail(){
        OrderService orderService = new OrderServiceImpl();
        List<OrderItem> orderItems = orderService.showOrderDetail(new Order("16232927072543", null, null, null, null));
        System.out.println(orderItems);

    }
}