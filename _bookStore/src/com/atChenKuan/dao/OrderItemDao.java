package com.atChenKuan.dao;

import com.atChenKuan.pojo.OrderItem;

/**
 * @author 陈宽
 * @create 2021-05-09 15:12
 * @description  保存订单项
 */
public interface OrderItemDao {

    public int saveOrderItem(OrderItem orderItem);

}
