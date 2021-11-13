package com.example._Response;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 陈宽
 * @create 2021-02-09 14:55
 * @descirbe 请求重定向 页面1
 */
public class Response_1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Response_1响应");

        //不共享 Request 域中的数据。  因为一次请求就封装一次Request对象，重定向是两次不同的请求
//        req.setAttribute("key1","value1");

        //设置响应状态码302，表示重定向，(已搬迁)
//        resp.setStatus(302);
        //设置响应头,说明 新的地址在哪里
        //不能访问WEB-INF目录下的文件，但可以访问工程外的文件(其它网站)
//        resp.setHeader("Location","http://localhost:8080/_byServlet/Response_2");

        //推荐方法，一行代码使用重定向跳转
        resp.sendRedirect("http://localhost:8080/_byServlet/Response_2");
    }
}
