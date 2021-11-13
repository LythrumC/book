package com.atChenKuan.web;

import com.atChenKuan.pojo.Book;
import com.atChenKuan.pojo.Cart;
import com.atChenKuan.pojo.CartItem;
import com.atChenKuan.service.BookService;
import com.atChenKuan.service.impl.BookServiceImpl;
import com.atChenKuan.utils.webUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 陈宽
 * @create 2021-05-07 22:07
 * @description
 */
public class CartServlet extends baseServlet{
    BookService bookService = new BookServiceImpl();

    /**
     * 通过Session将商品加入购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数，商品编号
        int id = webUtils.parseInt(req.getParameter("id"), 0);
        //2.通过queryByID，去数据库查找匹配ID的图书
        Book book = bookService.queryBookById(id);
        //3.把Book对象，转化为CartItem对象                                                            单个商品的总价和单价都一样
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        //4.先查找Session里面是不是已经有Cart对象，避免重复创建购物车，造成商品丢失
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart == null){
            //如果没有，就创建新的Cart对象，并放进Session里面
             cart = new Cart();
             req.getSession().setAttribute("cart",cart);
        }
        //把最后添加的商品存进Session
        req.getSession().setAttribute("lastCartItem",cartItem.getName());
        //5.通过Cart.addItem()把商品加入购物车
        cart.addItem(cartItem);
        System.out.println(cart);
        //Referer请求头获取浏览器的顶部栏目的   当前浏览器地址！！
//        System.out.println("请求头Referer的值:" + req.getHeader("Referer"));

        //6.重定向到当前购物车页面,这里不能用req.getContentPath()，因为会重定向到首页，必须定位到当前加入购物车的页面
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * 使用AJAX实现additem加入购物车功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItembyAjax(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数，商品编号
        int id = webUtils.parseInt(req.getParameter("id"), 0);
        //2.通过queryByID，去数据库查找匹配ID的图书
        Book book = bookService.queryBookById(id);
        //3.把Book对象，转化为CartItem对象                                                            单个商品的总价和单价都一样
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        //4.先查找Session里面是不是已经有Cart对象，避免重复创建购物车，造成商品丢失
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart == null){
            //如果没有，就创建新的Cart对象，并放进Session里面
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        //5.通过Cart.addItem()把商品加入购物车
        cart.addItem(cartItem);
        System.out.println(cart);
        //把最后添加的商品存进Session
        req.getSession().setAttribute("lastCartItem",cartItem.getName());

//        System.out.println(cart);
        //Referer请求头获取浏览器的顶部栏目的   当前浏览器地址！！
//        System.out.println("请求头Referer的值:" + req.getHeader("Referer"));

        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("totalCount",cart.getTotalCount());  //购物车商品总数量
        resultMap.put("lastCartItem",cartItem.getName());  //最后加入购物车的商品
        //把传送的数据转换为JSON格式
        Gson gson = new Gson();
        String resultMapObj = gson.toJson(resultMap);
        resp.getWriter().write(resultMapObj);
    }

    /**
     * 删除当前商品项
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("进入删除");
        //得到当前商品ID
        int id = webUtils.parseInt(req.getParameter("id"),0);
        //得到当前购物车
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //如果当前购物车不为空，就删除这个商品
        if(cart != null){
            cart.deleteItem(id);
            //6.重定向到当前购物车页面,这里不能用req.getContentPath()，因为会重定向到首页，必须定位到当前加入购物车的页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    protected void clearItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //先得到当前的购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //如果购物车不为空就全部清除
        if(cart != null){
            cart.clear();             resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     * 修改购物车中商品的数量
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取商品的编号，和要修改的数量
        int id = webUtils.parseInt(req.getParameter("id"),0);
        int count = webUtils.parseInt(req.getParameter("count"),0);

        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart != null){
            cart.updateCount(id,count);
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
}
