package com.atChenKuan.Servlet;

import com.atChenKuan.Utils.CookieUtil;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 陈宽
 * @create 2021-04-24 14:51
 * @description
 */
public class CookieServlet extends baseServlet {

    /**
     * Cookie的path并不是存储地址，而是过滤器！
     * 如果符合工程路径，cookie才会发送到客户端上，如果不符合就不会发送
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void SetCookiePath(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         Cookie cookie_1 = new Cookie("cookie_A","cookie_A");
         //得到工程路径
         cookie_1.setPath(req.getContextPath() + "/abc");  // 工程路径/abc
        resp.addCookie(cookie_1);
        resp.getWriter().write("创建了一个有Path路径的Cookie");

    }

    /***
     * 测试默认生命周期
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void defaultLife(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie defaultCookie = new Cookie("defaultLife", "defaultLife");
        //方法SetmaxAge()是改变生命周期
        //负数，表示浏览器一关，Cookie就会删除
//          defaultCookie.setMaxAge(-1); //默认就是-1
        //正数，表示在指定的秒数后过期
            defaultCookie.setMaxAge(60 * 60);  //存活3600s 一小时

        //0，表示马上删除Cookie

        resp.addCookie(defaultCookie);
        resp.getWriter().write(defaultCookie.getName() + "被创建");

    }

    /**
     * 立马删除cookie
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteNow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       Cookie cookie_1 = CookieUtil.findCookie("key_1",req.getCookies());
       if(cookie_1 != null){
           //立马被删除
           cookie_1.setMaxAge(0);
           resp.addCookie(cookie_1);
           resp.getWriter().write(cookie_1.getName() + "被删除");
       }

    }

    /**
     * 设置Cookie存活3600s
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void Life3600S(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie Life3600 = new Cookie("Life3600", "Life3600");
        //方法SetmaxAge()是改变生命周期
        //负数，表示浏览器一关，Cookie就会删除
//          defaultCookie.setMaxAge(-1); //默认就是-1
        //正数，表示在指定的秒数后过期
        Life3600.setMaxAge(60 * 60);  //存活3600s 一小时

        //0，表示马上删除Cookie

        resp.addCookie(Life3600);
        resp.getWriter().write(Life3600.getName() + "被创建3600s");  //格林威治时间 GMT

    }


    protected void UpdateCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /** 方案一：
         * 1.创建一个同名的需要修改的cookie对象
         * 2.在构造器中，同时赋予cooie新的值
         * 3.调用resp.addCookie( Cookie )进行提交
         * 原理：当打开服务器时，回去查看客户端有没有cookie，没有就创建，有就修改
         */
        Cookie cookie_1 = new Cookie("key_1", "UpdatekEY");
        resp.addCookie(cookie_1);
        resp.getWriter().write(cookie_1.getName() + "的值已经被修改" + cookie_1.getValue());

        /** 方案二：
         * 1.遍历cookies对象，找到自己需要修改的cookie
         * 2.调用 cookie.setValue()方法进行值的修改
         * 3.调用resp.addCookie( Cookie )进行提交
         * 原理：当打开服务器时，回去查看客户端有没有cookie，没有就创建，有就修改
         */
        Cookie cookie_2 = CookieUtil.findCookie("key_2", req.getCookies());
        if (cookie_2 != null) {
            cookie_2.setValue("UpdateKEY2");
            resp.addCookie(cookie_2);
        }


    }

    protected void getCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //从服务器中获取信息要用request，所以获取传进服务器的cookie要用req
        //在request hearder里面 有一个Cookie，从这里面获取数据，里面是多个键值对

        Cookie iWantCookie = CookieUtil.findCookie("key_2", req.getCookies());
        resp.getWriter().write(iWantCookie.getName() + "被找到!");


//        Cookie[] cookies = req.getCookies();
//        for (Cookie cookie : cookies) {
//            if("key_2".equals(cookie.getName())){
//                iWantCookie = cookie;
//
//            }
//
//            if (iWantCookie != null){
//                resp.getWriter().write(cookie.getName() + "被找到了" + "</br>");
//            }
//        }


    }

    protected void CreateCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //cookie在浏览器中通过response header进行保存
        //在打开浏览器时，回去查看response header 里面有没有 set-cookie这个参数，如果没有就创建一个相对应的Cookie，有就进行修改

        //1.创建cookie,cookie可以创建多个对象
        Cookie cookie_1 = new Cookie("key_1", "value_1");
        Cookie cookie_2 = new Cookie("key_2", "value_2");
        //2.通知response保存Cookie!!!  这一个步骤非常重要
        resp.addCookie(cookie_1);
        resp.addCookie(cookie_2);
        //3.给cookie_1和cookie_2加点内容
        resp.getWriter().write(cookie_1.getName() + "被创建成功");
        resp.getWriter().write(cookie_2.getName() + "被创建成功");


    }
}
