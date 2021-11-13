package com.atChenKuan.Servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 陈宽
 * @create 2021-05-10 12:06
 * @description
 */
public class loginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("进入doGet");
        //获得登录页面的信息
        resp.setContentType("text/html; charset=UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //举例
        if("admin".equals(username) && "admin".equals(password)){
            req.getSession().setAttribute("username",username);
            resp.getWriter().write("登录成功！");
        }else{
            //登录失败，跳转到登录页面继续登录
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        }
    }
}
