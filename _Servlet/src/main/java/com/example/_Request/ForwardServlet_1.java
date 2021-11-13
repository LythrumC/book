package com.example._Request;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 陈宽
 * @create 2021-02-05 19:13
 */
public class ForwardServlet_1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数(手持材料)
        String username = req.getParameter("username");
        System.out.println("在ForwardServlet_1中查看参数:" + username);

        //设置一个全域范围的信息(确认盖章)
        req.setAttribute("key", "ForwardServlet_1的确认信息");


        //  /请求转发必须以斜杠开头， /斜杠地址表示为：http:ip:port/工程名/, 映射到idea目录为  webapp
        //  可以转向本地地址(WEB-INF中的)，只能由请求转，不能通过浏览器
//        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/demo_04.html");
        //指示当前Servlet页面转向 哪一个 Servlet页面(指路)
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/ForwardServlet_2");
        //进行跳转操作(走到下一个柜台进行操作)
        requestDispatcher.forward(req,resp);
    }
}
