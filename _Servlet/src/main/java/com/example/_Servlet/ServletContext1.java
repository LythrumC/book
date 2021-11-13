package com.example._Servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 陈宽
 * @create 2021-02-04 16:35
 * @describe 测试使用servletContext存取数据
 */
public class ServletContext1 extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        //getattribute()取数据
        System.out.println("servletContext1 Key存数据之前的信息" + servletContext.getAttribute("key"));
        //存数据
        servletContext.setAttribute("key","value");
        System.out.println("servletContext1 Key存数据之后的信息" + servletContext.getAttribute("key"));
    }
}
