package com.example._Servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author 陈宽
 * @create 2021-02-03 14:28
 * @describe 手动启动Servlet
 *    1.编写一个类去实现Servlet接口
 *    2.实现service方法，处理请求，并响应数据
 *    3.到web.xml中去配置
 */
public class HelloServlet implements Servlet {

    public HelloServlet(){
        System.out.println("1.执行类构造方法");
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
//        System.out.println("2.执行init方法");
        //ServletConfig 类可以获取Servlet的配置信息 (xml)
        //1.获取Servlet程序的别名Servlet-name的值
        System.out.println("HelloServlet程序的别名:" + servletConfig.getServletName());
        //2.获取初始化参数init-parm
        System.out.println("初始化参数username的值是:" + servletConfig.getInitParameter("username"));
        //3.获取ServletContext对象
        System.out.println(servletConfig.getServletContext());

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    /**
     * Service方法专门用来处理请求和响应
     * */
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("3.servelet 被响应");
         //类型转换 (因为它有getMethod（）方法)
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        //获得请求方式
        String method = httpServletRequest.getMethod();
        if ("GET".equals(method)){
            getGet();
        }else {
            getPost();
        }

    }

    public void getPost(){
        System.out.println("得到post方法");
    }

    public void getGet(){
        System.out.println("得到Get方法");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("4.执行销毁方法");

    }


}
