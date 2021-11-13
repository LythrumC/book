package com.atChenKuan.Filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author 陈宽
 * @create 2021-05-10 13:35
 * @description
 */
public class Filter_1 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
             System.out.println("Filter_1的前置代码");
             //Filter过滤链是共享 request域中的数据的我的。
             System.out.println(servletRequest.getParameter("username"));
             System.out.println("Filter1的线程:" + Thread.currentThread());
             //当执行到这个代码时，过滤器会再去寻找其他过滤器执行。然后再逐层返回执行下面的代码
             filterChain.doFilter(servletRequest,servletResponse);
             System.out.println("Filter_1的后置代码");
    }

    @Override
    public void destroy() {

    }
}
