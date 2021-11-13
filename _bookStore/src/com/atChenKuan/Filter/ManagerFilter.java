package com.atChenKuan.Filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author 陈宽
 * @create 2021-05-10 19:22
 * @description  managerFilter过滤器，要求用户必须登录才能访问后台系统
 */
public class ManagerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * 权限控制器，要过滤的文件需要在web.xml中配置
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //1.获取HttpServletRequest（servletRequest是HttpServletRequest的父类，他没有实现getSession()具体方法，所以要转换成子类）
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        //2.获取session中的相关数据
        Object admin = httpServletRequest.getSession().getAttribute("admin");
        //3.进行逻辑判断（若没登录就转到登录页面）
        if(admin == null){
            httpServletRequest.getRequestDispatcher("/pages/user/login_info.jsp").forward(servletRequest,servletResponse);
        }else{
            //4.若有用户数据，就继续向下执行代码
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
