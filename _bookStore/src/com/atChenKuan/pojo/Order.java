package com.atChenKuan.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 陈宽
 * @create 2021-05-09 14:58
 * @description
 */
public class Order {
    private String orderID;
    private Date createTime;
    private BigDecimal price;
    //status是发货状态 0是未发货，1是已发货，2是已完成
    private Integer status = 0;
    private Integer user_id;

    public Order() {
    }

    public Order(String orderID, Date createTime, BigDecimal price, Integer status, Integer user_id) {
        this.orderID = orderID;
        this.createTime = createTime;
        this.price = price;
        this.status = status;
        this.user_id = user_id;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "order{" +
                "orderID='" + orderID + '\'' +
                ", createTime=" + createTime +
                ", price=" + price +
                ", status=" + status +
                ", user_id=" + user_id +
                '}';
    }
}
