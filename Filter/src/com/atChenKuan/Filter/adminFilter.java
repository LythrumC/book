package com.atChenKuan.Filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author 陈宽
 * @create 2021-05-10 10:48
 * @description
 */
public class adminFilter implements Filter {
    public adminFilter(){
        System.out.println("生命周期:1.adminFilter()构造器方法");
    }



    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("生命周期:2.init()方法");
        //FilterConfig类
        //1.获取Filter的名称，即web.xml中file-name的内容
        System.out.println("Filter名称:" + filterConfig.getFilterName());
        //2.获取Filter中配置的init-param初始化参数
        System.out.println("Filter中init-param参数：" + filterConfig.getInitParameter("username"));
        System.out.println("Filter中init-param参数：" + filterConfig.getInitParameter("url"));
        //3.获取ServletConfig类对象
        System.out.println("Filter获取ServletConfig对象:" + filterConfig.getServletContext());

    }

    /**
     * doFilter方法，专门用来拦截请求，可以做权限检查
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("生命周期:3.doFilter()方法");
        //把servletRequest强制转换成httpServletRequest
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        //得到Session
        Object username = httpServletRequest.getSession().getAttribute("username");
        //权限判断
        if(username == null){
            httpServletRequest.getRequestDispatcher("/login.jsp").forward(servletRequest,servletResponse);
            return;
        }else{
            //如果有权限,就继续向下执行
            filterChain.doFilter(servletRequest,servletResponse);
        }

    }

    @Override
    public void destroy() {
        System.out.println("生命周期:4.destroy()方法");
    }
}
