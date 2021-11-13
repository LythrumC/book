package com.atChenKuan;

/**
 * @author 陈宽
 * @create 2021-05-12 10:10
 * @description
 */
public class OrderService {

    public void onCreate(){
        String name = Thread.currentThread().getName();  //得到当前线程名字
//        System.out.println("OrderService当前线程【"+ name +"】中保存得数据时: " + ThreadLocalTest.data.get(name));
        System.out.println("OrderService当前线程【"+ name +"】中保存得数据时: " + ThreadLocalTest.threadLocal.get());
        new orderDao().saveOrder();
    }
}
