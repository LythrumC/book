package com.example._Request;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
@author 陈宽
@create 2021-02-05 19:13
*/public class ForwardServlet_2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        System.out.println("在ForwardSerlvet_1中查看的参数为：" + username);

        Object key = req.getAttribute("key");
        System.out.println("查看是否有ForwardServlet_1的确认信息" + key);

        System.out.println("ForwardServlet_2处理自己的业务");
    }
}
