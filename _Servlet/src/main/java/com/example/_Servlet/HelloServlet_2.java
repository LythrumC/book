package com.example._Servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 陈宽
 * @create 2021-02-03 16:53
 */
                                     //一般继承HttpServlet
public class HelloServlet_2 extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);//super.init(config)是非常重要的，必须写，不然报空指针异常
    }

    //表单为get时执行这个方法
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HelloServlet2 的 doGet方法");

        //手动获取servletConfig(),只能获取自己(当前类)的web.xml配置信息!!!
        ServletConfig servletConfig = this.getServletConfig();

        //1.获取Servlet程序的别名Servlet-name的值
        System.out.println("HelloServlet程序的别名:" + servletConfig.getServletName());
        //2.获取初始化参数init-parm
        System.out.println("初始化参数username的值是:" + servletConfig.getInitParameter("username"));
        //3.获取ServletContext对象
        System.out.println(servletConfig.getServletContext());
    }

    //表单为post时执行这个方法
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       System.out.println("HelloServlet2 的 doPost方法");
    }
}
