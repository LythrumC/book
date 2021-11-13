package com.example._Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 陈宽
 * @create 2021-02-04 15:58
 * @describe 测试使用servletContext上下文来获取xml文件中上下文属性
 */
@WebServlet(name = "ServletContext")
public class ServletContext extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        1.获取web.xml中配置的上下文参数 context-param
        javax.servlet.ServletContext servletContext = getServletConfig().getServletContext();
        System.out.println("context-param参数的username值是：" + servletContext.getInitParameter("username"));
        System.out.println("context-param参数的password值是：" + servletContext.getInitParameter("password"));
//        2.获取当前的工程路径，格式：/工程路径
        System.out.println("当前工程的路径是：" + servletContext.getContextPath());  //相对路径
//        3.获取工程部署后在服务器硬盘上的绝对路径
        /**
         *  / 斜杠被服务器解析地址为：http://ip:port/工程名/   映射到idea代码的webapp目录中
         */
        System.out.println("工程部署的路径是: " + servletContext.getRealPath("/"));  //绝对路径
        System.out.println("工程部署下imgs的路径是：" + servletContext.getRealPath("/imgs"));
        System.out.println("工程部署下imgs中1.jpg路径是：" + servletContext.getRealPath("/imgs/1.jpg"));
    }
}
