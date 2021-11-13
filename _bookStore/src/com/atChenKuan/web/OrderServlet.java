package com.atChenKuan.web;

import com.atChenKuan.pojo.Cart;
import com.atChenKuan.pojo.Order;
import com.atChenKuan.pojo.OrderItem;
import com.atChenKuan.pojo.User;
import com.atChenKuan.service.OrderService;
import com.atChenKuan.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author 陈宽
 * @create 2021-05-09 16:44
 * @description
 */
public class OrderServlet extends baseServlet{

    /**
     * 此方法的order订单是存放在session里面的
     * 所以只要不关闭浏览器，只要是登录过的用户，都可以查看到订单信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //打印该方法所在线程的名字
//        System.out.println("createOrder方法" + Thread.currentThread().getName());
        //1.获取用户id和购物车
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        User user = (User) req.getSession().getAttribute("user");
        //2.验证用户是否登录(user是否为空),为空则跳转到登录界面
        if(user == null){
            req.getRequestDispatcher("/pages/user/login_info.jsp").forward(req,resp);
            return ;
        }
        //3.创建订单
        OrderService orderService = new OrderServiceImpl();
        String orderID = orderService.createOrder(cart,user.getId());
        req.getSession().setAttribute("orderID",orderID);
        //4.跳转到订单页面(为了防止表单重复提交，要用重定向)
//        req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req,resp);
        resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");
    }

    /**
     * 显示我自己的订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showMyOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取登录的用户对象User和购物车对象Cart
        User user = (User) req.getSession().getAttribute("user");
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //2.调用OrderService查询自己的订单
        OrderService orderService = new OrderServiceImpl();
        List<Order> orders = orderService.showMyOrders(user);
        //3.将订单信息保存到session域中，然后再jsp页面调用显示
        req.getSession().setAttribute("myOrder",orders);
        req.getRequestDispatcher("/pages/order/userOrder.jsp").forward(req,resp);
    }

    /**
     * 显示所有订单(管理员)
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showAllOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.因为是管理员，直接就可以查看所有订单，但是还是要检查一遍权限.
        if( req.getSession().getAttribute("admin") == null){
            req.getRequestDispatcher("/pages/user/login_info.jsp").forward(req,resp);
        }
        //如果是管理员，就可以查看
        else{
            //2.调用OrderService查询所有订单
            OrderService orderService = new OrderServiceImpl();
            List<Order> allOrders = orderService.showAllOrders();
            req.getSession().setAttribute("myOrder",allOrders);
            req.getRequestDispatcher("/pages/order/userOrder.jsp").forward(req,resp);
        }
    }

    /**
     * 显示该订单的订单项详情
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void orderDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取<a>标签上标记的订单编号
        String orderId = req.getParameter("orderId");
        //2.调用OrderService查询订单详情信息
        OrderService orderService = new OrderServiceImpl();
                                               //因为只需要orderId就行了，其他不需要
        List<OrderItem> orderDetails = orderService.showOrderDetail(new Order(orderId, null, null, null, null));
        //3.将查询到的orderDetails发送到jsp页面
        req.getSession().setAttribute("orderDetails",orderDetails);
        //4.跳转到显示页面
        req.getRequestDispatcher("/pages/order/orderDetails.jsp").forward(req,resp);
    }
}
