package com.atChenKuan.dao.impl;

import com.atChenKuan.dao.OrderDao;
import com.atChenKuan.pojo.Order;
import com.atChenKuan.pojo.OrderItem;
import com.atChenKuan.pojo.User;

import java.util.List;

/**
 * @author 陈宽
 * @create 2021-05-09 15:13
 * @description
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {
    /**
     * 传进订单，保存订单
     * @param order 订单对象
     * @return
     */
    public int saveOrder(Order order) {
        //打印该方法所在线程的名字
//        System.out.println("OrderDaoImpl所在线程的名字:" + Thread.currentThread().getName());
        String sql = "insert into t_order(order_id,create_time,price,status,user_id) values (?,?,?,?,?)";
        return update(sql,order.getOrderID(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUser_id());
    }

    /**
     * 查询当前用户的订单信息
     * @param userId
     * @return
     */
    @Override
    public List<Order> queryForOrderByUserid(Integer userId)
    {
        String sql = "select order_id as OrderID, create_time as createTime, price, status, user_id from t_order where user_id = ?";
        return queryForList(Order.class,sql,userId);
    }

    /**
     * 查询所有的订单信息(管理员)
     * @return
     */
    @Override
    public List<Order> queryForAllOrder() {
        String sql = "select order_id as OrderID, create_time as createTime, price, status, user_id from t_order";
        return queryForList(Order.class,sql);
    }


    @Override
    public List<OrderItem> queryForOrderItemsByOrderId(Order order) {
        String sql = "select id, name, count, price, total_price as totalPrice, order_id as orderId  from t_order_item where order_id = ?";
        return queryForList(OrderItem.class,sql,order.getOrderID());
    }
}
