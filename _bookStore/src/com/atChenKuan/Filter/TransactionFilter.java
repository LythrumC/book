package com.atChenKuan.Filter;

import com.atChenKuan.utils.dbcpUtils;
import org.apache.commons.dbutils.DbUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author 陈宽
 * @create 2021-05-12 14:25
 * @description  该过滤器用来统一管理 Threadlocal中的conn连接以及事务处理。 程序执行时会先处理过滤器
 *
 */
public class TransactionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * 2021年6月24日14:55:59 这里写下关于doFilter执行顺序的笔记
     *     1.filterChain.doFilter() 时执行链，当没有需要执行的下一个Filter时，就执行正常的代码
     *     2.web.xml配置的过滤对象都是jsp页面，不是类。也就是说，当访问有过滤器的jsp页面时，doFilter()方法就生效
     *     3.这里dbcpUtils.commiteAndClose()是怎样执行的？
     *        答：因为没有下一个Filter，所以filterChain.doFilter()直接执行代码。
     *        例如现在在jsp页面点击后台管理后，依次执行bookServlet的page() --> bookService.queryBooks() -->
     *        bookDao.queryBooks()  --> baseDao.queryForList 这样一个顺序
     *        而当到了baseDao时，因为所有的方法都是先得到conn，然后执行sql返回结果，然后关闭conn，但是因为加了事务
     *        和过滤器，所以baseDao任意一个方法执行完后，就跳到本Filter里面，执行 dbcpUtils.commitAndClose();
     *        如果期间任何一行代码出现了问题，就会抛到Filter里面，执行dbcpUtils.rollBackAndClose();
     *
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            //过滤器先向下执行web.xml里的url()中配置的相关路径文件代码，然后再返回继续向下执行
            filterChain.doFilter(servletRequest,servletResponse);
            //如果没有异常，则提交事物并关闭连接
            dbcpUtils.commitAndClose();
         }catch(Exception e) {
            //如果有异常，捕捉从baseServlet中抛出的异常,回滚事物
            dbcpUtils.rollBackAndClose();
            /**
             * 这里为什么要排除异常？
             * 过滤器已经是比较上层的代码，还比他更上层的就是Tomcat服务器，这里排除异常给的是Tomcat
             * 因为在web.xml中配置了errorPage，要让tomcat识别，必须抛出响应的异常给tomcat
             */
            throw new RuntimeException(e);
        };
    }

    @Override
    public void destroy() {

    }
}
