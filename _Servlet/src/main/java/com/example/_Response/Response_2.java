package com.example._Response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 陈宽
 * @create 2021-02-09 14:55
 * @descirbe 请求重定向页面2
 */
public class Response_2 extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("由Response_1跳转到Response_2");
        System.out.println(request.getAttribute("key1"));
        response.getWriter().write("response_2's result");
    }
}
