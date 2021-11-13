package com.example._Servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 陈宽
 * @create 2021-02-04 16:39
 * @describe 在ServletContext1 存数据后，servletContext2也能获取到数据，这说明ServletContext存数据的作用域是整个web工程
 */
public class ServletContext2 extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        System.out.println("servletContext2 获取 servletContext1的信息:" + servletContext.getAttribute("key"));
    }
}
