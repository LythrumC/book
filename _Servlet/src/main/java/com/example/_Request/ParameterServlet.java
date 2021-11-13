package com.example._Request;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author 陈宽
 * @create 2021-02-05 12:14
 * @describe 使用RequestServlet中的 getParameter()方法获取表单中提交的参数
 */
public class ParameterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("---------doget-----------");
        //获取demo_04.html 网页中表单的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String[] hobbies = req.getParameterValues("hobby");

        System.out.println("用户名:" + username);
        System.out.println("密码:" + password);
        System.out.println("爱好:" + Arrays.asList(hobbies));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("---------dopost-----------");
        //解决post乱码问题,参数获取之前调用
        req.setCharacterEncoding("UTF-8");
        //获取demo_04.html 网页中表单的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String[] hobbies = req.getParameterValues("hobby");

        System.out.println("用户名:" + username);
        System.out.println("密码:" + password);
        System.out.println("爱好:" + Arrays.asList(hobbies));
    }
}
