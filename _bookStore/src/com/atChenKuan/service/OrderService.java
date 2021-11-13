package com.atChenKuan.service;

import com.atChenKuan.pojo.Cart;
import com.atChenKuan.pojo.Order;
import com.atChenKuan.pojo.OrderItem;
import com.atChenKuan.pojo.User;

import java.util.List;

/**
 * @author 陈宽
 * @create 2021-05-09 15:55
 * @description
 */
public interface OrderService {

    /**
     * 创建订单并且保存订单和订单项
     * @param cart
     * @param userID
     * @return
     */
    public String createOrder(Cart cart, Integer userID);

    /**
     * 查看所有订单（管理员才能查看）
     */
    public List<Order> showAllOrders();

    /**
     * 发货
     * @param order 订单信息
     */
    public void sendOrder(Order order);

    /**
     * 查看某一个订单详情
     * @param order 订单信息
     */
    public List<OrderItem> showOrderDetail(Order order);

    /**
     *  查看我的订单(当前用户的订单)
     * @param user 当前用户
     */
    public List<Order> showMyOrders(User user);

    /**
     * 签收订单/确认收货
     * @param order 订单信息
     */
    public void receiverOrder(Order order);



}
