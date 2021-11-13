package com.atChenKuan.test;

import com.atChenKuan.dao.OrderDao;
import com.atChenKuan.dao.UserDao;
import com.atChenKuan.dao.impl.OrderDaoImpl;
import com.atChenKuan.dao.impl.UserDaoImpl;
import com.atChenKuan.pojo.Order;
import com.atChenKuan.pojo.OrderItem;
import com.atChenKuan.pojo.User;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author 陈宽
 * @create 2021-05-09 15:22
 * @description
 */
public class OrderDaoImplTest {

    @Test
    public void saveOrder() {
        OrderDao orderDao = new OrderDaoImpl();
        //user_id必须和book_user表(User对象)中的id一样，因为他是外键
        orderDao.saveOrder(new Order("170306001",new Date(),new BigDecimal(100),0,1));
    }

    @Test
    public void queryForOrderByUserId(){
        OrderDao orderDao = new OrderDaoImpl();
        UserDao userDao = new UserDaoImpl();
        List<Order> ckOrder = orderDao.queryForOrderByUserid(userDao.queryUserByUsername("ck").getId());
        //输出订单
        System.out.println(ckOrder);
        //输出订单详情
        System.out.println(ckOrder.get(0).toString());
    }

    @Test
    public void queryForAllOrder(){
        OrderDao orderDao = new OrderDaoImpl();
        System.out.println(orderDao.queryForAllOrder());
    }

    @Test
    public void queryForOrderItemsByOrderId(){
        OrderDao orderDao = new OrderDaoImpl();
        List<OrderItem> orderItems = orderDao.queryForOrderItemsByOrderId(new Order("16232927072543", new Date(), new BigDecimal(123), 0, 3));
        System.out.println(orderItems);
    }
}