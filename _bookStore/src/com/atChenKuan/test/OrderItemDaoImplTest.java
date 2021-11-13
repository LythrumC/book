package com.atChenKuan.test;

import com.atChenKuan.dao.OrderItemDao;
import com.atChenKuan.dao.impl.OrderItemDaoImpl;
import com.atChenKuan.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author 陈宽
 * @create 2021-05-09 15:26
 * @description
 */
public class OrderItemDaoImplTest {

    @Test
    public void saveOrderItem() {
        OrderItemDao orderItemDao = new OrderItemDaoImpl();
        //orderID是外键，必须和Order中的Orderid一样，才能查找
        //orderItem的id是自增字段，可以给一个null值
        orderItemDao.saveOrderItem(new OrderItem(null,"java从入门到精通",new Integer(1), new BigDecimal(100),new BigDecimal(100),"170306001"));
        orderItemDao.saveOrderItem(new OrderItem(null,"javaScript从入门到精通",new Integer(1), new BigDecimal(100),new BigDecimal(100),"170306001"));
        orderItemDao.saveOrderItem(new OrderItem(null,"C语言从入门到精通",new Integer(1), new BigDecimal(100),new BigDecimal(100),"170306001"));
        orderItemDao.saveOrderItem(new OrderItem(null,"C#从入门到精通",new Integer(1), new BigDecimal(100),new BigDecimal(100),"170306001"));
    }
}