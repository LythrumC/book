package com.atChenKuan.dao.impl;

import com.atChenKuan.dao.OrderItemDao;
import com.atChenKuan.pojo.OrderItem;

/**
 * @author 陈宽
 * @create 2021-05-09 15:17
 * @description
 */
public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {

    /**
     *  传进订单项，保存订单项
     * @param orderItem
     * @return
     */
    public int saveOrderItem(OrderItem orderItem) {
        //打印该方法所在线程的名字
//        System.out.println("OrderItem所在线程的名字:" + Thread.currentThread().getName());
                                      //这里不写id，因为id是auto_increment的Primary Key
        String sql = "insert into t_order_item(name,count,price,total_price,order_id) values (?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderID());
    }
}
