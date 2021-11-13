package com.atChenKuan.dao;

import com.atChenKuan.pojo.Order;
import com.atChenKuan.pojo.OrderItem;
import com.atChenKuan.pojo.User;

import java.util.List;

/**
 * @author 陈宽
 * @create 2021-05-09 15:11
 * @description 订单Dao层
 */
public interface OrderDao {

    /**
     * 保存订单
     * @param order 订单对象
     * @return  返回整数则执行成功
     */
    public int saveOrder(Order order);

    /**
     * 根据传递的User用户对象，查询该用户的所有订单
     * @param userId
     * @return
     */
    public List<Order> queryForOrderByUserid(Integer userId);

    /**
     * 查询所有订单(管理员)
     * @return
     */
    public List<Order> queryForAllOrder();

    /**
     * 查询订单项
     * @param order
     * @return
     */
    public List<OrderItem> queryForOrderItemsByOrderId(Order order);



}
