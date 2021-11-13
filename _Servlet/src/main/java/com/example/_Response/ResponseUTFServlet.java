package com.example._Response;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 陈宽
 * @create 2021-02-09 14:19
 * @describe 往客户端回传字符串传，以UTF8编码形式。
 */
public class ResponseUTFServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println(resp.getCharacterEncoding());   //得到字符编码，默认为ISO

        //设置 服务器编码格式 为UTF8
//        resp.setCharacterEncoding("UTF-8");

        //还要改变浏览器的编码形式,通过响应头改变
//        resp.setHeader("Content-Type","text/html; charset=UTF-8");

        //一行代码改变 服务器 和 浏览器的编码
        resp.setContentType("text/html; charset=UTF-8");

        //要求:往客户端回传字符串
        PrintWriter printWriter = resp.getWriter();
        printWriter.write("测试编写响应");
    }
}
