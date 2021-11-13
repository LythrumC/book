package com.example._Request;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 陈宽
 * @create 2021-02-05 11:56
 * @describe  测试使用RequestServlet类，作用是请求获取http封装的request请求对象信息
 */
public class RequsetAPIServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1.getRequestURI()     获取请求的资源路径
        System.out.println("URI ->" + req.getRequestURI());
//        2.getRequestURL()     获取请求的统一资源定位符(绝对路径)
        System.out.println("URL ->" + req.getRequestURL());
//        3.getRemoteHost()     获取客户端的IP地址
        /*
        *  用localhost访问时，  IP是 127.0.0.1
        *  用127.0.0.1访问时，  IP是127.0.0.1
        *  用真实IP地址访问时，  IP是真实地址
        * */
        System.out.println("客户端IP地址 ->" + req.getRemoteHost());
//        4.getHeader()         获取请求头
        System.out.println("请求头User—Agent ->" + req.getHeader("User-Agent"));
//        7.getMethod()         获取请求的方式get或post
        System.out.println("获取请求方式 ->" +req.getMethod());
    }
}
