package com.atChenKuan.service.impl;

import com.atChenKuan.dao.OrderDao;
import com.atChenKuan.dao.OrderItemDao;
import com.atChenKuan.dao.impl.OrderDaoImpl;
import com.atChenKuan.dao.impl.OrderItemDaoImpl;
import com.atChenKuan.pojo.*;
import com.atChenKuan.service.BookService;
import com.atChenKuan.service.OrderService;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 陈宽
 * @create 2021-05-09 15:57
 * @description
 */
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookService bookService = new BookServiceImpl();

    /**
     * 创建订单并且保存订单和订单项(核心就是把购物车和购物车项转化为订单和订单项)
     * @param cart  购物车
     * @param userID  登录的用户id
     * @return
     */
    public String createOrder(Cart cart, Integer userID) {
        //打印该方法所在线程的名字
//        System.out.println("OrderServiceImpl所在线程的名字:" + Thread.currentThread().getName());
        //生成订单号，时间戳+用户ID
        String orderID = System.currentTimeMillis() + "" + userID;
        //创建一个订单对象
        Order order = new Order(orderID,new Date(),cart.getTotalPrice(),0,userID);
        //保存订单
        orderDao.saveOrder(order);

        //遍历购物车物品项，把他们全部转化成订单项
        for(Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()){
            CartItem cartItem = entry.getValue();
            //转化成订单项
            OrderItem orderItem = new OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(),orderID);
            //保存订单项
            orderItemDao.saveOrderItem(orderItem);

            //手动添加一个异常，用来测试事务
//            int i = 12/0;

            //在创建订单时，首页响应的物品项的数量和销量也应该变换
            Book book = bookService.queryBookById(cartItem.getId());
            book.setSales(book.getSales() + cartItem.getCount());  //销量增加
            book.setStock(book.getStock() - cartItem.getCount());  //库存减少
            bookService.updateBook(book);
        }

        //生成订单后，清空购物车
        cart.clear();
        //生成订单后，返回订单号
        return  orderID;
    }

    /**
     * 管理员查看所有订单
     */
    @Override
    public List<Order> showAllOrders() {
        List<Order> allOrders = orderDao.queryForAllOrder();
        return allOrders;
    }

    /**
     * 发货
     * @param order 订单信息
     */
    @Override
    public void sendOrder(Order order) {

    }

    /**
     * 查看某个订单详细信息
     * @param order 订单信息
     */
    @Override
    public List<OrderItem> showOrderDetail(Order order) {
        List<OrderItem> orderItems = orderDao.queryForOrderItemsByOrderId(order);
        return orderItems;
    }

    /**
     *  显示当前用户的订单信息
     * @param user 当前用户
     */
    @Override
    public List<Order> showMyOrders(User user) {
        //通过DAO查询该用户的订单信息，然后然后List<Order>列表
        List<Order> orders = orderDao.queryForOrderByUserid(user.getId());
        return orders;
    }

    /**
     * 签收货物/收货
     * @param order 订单信息
     */
    @Override
    public void receiverOrder(Order order) {

    }
}
