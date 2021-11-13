package com.atChenKuan.web;

import com.atChenKuan.pojo.Book;
import com.atChenKuan.pojo.Page;
import com.atChenKuan.service.BookService;
import com.atChenKuan.service.impl.BookServiceImpl;
import com.atChenKuan.utils.webUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 陈宽
 * @create 2021-03-26 21:01
 * @description
 */
public class clientBookServlet extends baseServlet {
    BookService bookService = new BookServiceImpl();

    /**
     * 处理分页功能(前台)
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1.获取请求参数pageNo和pageSize
        int pageNo = webUtils.parseInt(req.getParameter("pageNo"), 1);  //默认值是1
        int pageSize = webUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);   //默认值是PAGE_SIZE
        //2.调用Bookservice.page(pageNo,pageSize)
        Page<Book> page = bookService.page(pageNo, pageSize);
        //为什么用setURL？
        /*
        *   在jsp页面中，前台分页和后台分页，用的还是同一个page()分页方法，但是
        *   前台:client/clientBookServlet?action=page
        *   后台:manager/BookServlet?action=page
        *   他们两个经过的Servlet不同，为了节约在jsp页面中 <a href="请求地址">中的请求地址代码
        *   就把URL地址封装成Page对象后，再在自己对应的page()方法中将地址传进去
        *   这样就不用重复在jsp页面中写代码
        * */
        page.setUrl("client/clientBookServlet?action=page");
//        System.out.println(page.getUrl());
        //3.保存page对象到Request域中
        req.setAttribute("page", page);
        //4.请求转发到pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
//        System.out.println("进入前端分页的page方法末尾");
    }

    /**
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void PageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
//        1.获取请求参数pageNo, pageSize, min, max
        int pageNo = webUtils.parseInt(req.getParameter("pageNo"), 1);  //默认值是1
        int pageSize = webUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);   //默认值是PAGE_SIZE
        int min = webUtils.parseInt(req.getParameter("min"),0);  //默认值是0
        int max = webUtils.parseInt(req.getParameter("max"),Integer.MAX_VALUE); //默认值是最大值，我想数据库应该没有比Integer最大值还大的价格吧

        //2.调用Bookservice.pageByPrice(pageNo,pageSize) 按照价格分页
        Page<Book> page = bookService.pageByPrice(pageNo, pageSize, min, max);

        //线程不安全，但性能好，只是局部变量，无所谓的
        StringBuilder stringBuilder = new StringBuilder("client/clientBookServlet?action=PageByPrice");

        //以下2个if判断解决如下问题：
        //当按照价格区间进行搜索后，再点击下一页或者其他页码，会因为min和max没有传到相应的页码里面去，就会显示正常排序的数据
        if(req.getParameter("min") != null){
            stringBuilder.append("&min=").append(req.getParameter("min"));
        }

        if(req.getParameter("max") != null){
            stringBuilder.append("&max=").append(req.getParameter("max"));
        }

        //为什么用setURL？
        /*
         *   在jsp页面中，前台分页和后台分页，用的还是同一个page()分页方法，但是
         *   前台:client/clientBookServlet?action=page
         *   后台:manager/BookServlet?action=page
         *   他们两个经过的Servlet不同，为了节约在jsp页面中 <a href="请求地址">中的请求地址代码
         *   就把URL地址封装成Page对象后，再在自己对应的page()方法中将地址传进去
         *   这样就不用重复在jsp页面中写代码
         * */
        page.setUrl(stringBuilder.toString());


//        System.out.println(page.getUrl());
        //3.保存page对象到Request域中
        req.setAttribute("page", page);
        //4.请求转发到pages/manager/book_manager.jsp页面.
//        System.out.println("进入前端分页的pageByPrice方法末尾");
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);

    }

    /**
     * 根据关键字分页
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void PageByKeyName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取需要的参数
        String keyName = req.getParameter("keyName");
        int pageNo = webUtils.parseInt(req.getParameter("pageNo"), 1);  //默认值是1
        int pageSize = webUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);   //默认值是PAGE_SIZE
        //2.调用Bookservice.pageByPrice(pageNo,pageSize) 按照关键词分页
        Page<Book> page = bookService.pageByKeyName(pageSize,pageNo,keyName);

        //线程不安全，但性能好，只是局部变量，无所谓的
        StringBuilder stringBuilder = new StringBuilder("client/clientBookServlet?action=PageByKeyName");

        //以下1个if判断解决如下问题：
        //当按照关键词进行搜索后，再点击下一页或者其他页码，会因为挂念次没有传到相应的页码里面去，就会显示正常排序的数据
        if(req.getParameter("KeyName") != null){ stringBuilder.append("&KeyName=").append(req.getParameter("KeyName")); }
        page.setUrl(stringBuilder.toString());

        //3.保存page对象到Request域中
        req.setAttribute("page", page);
        //4.请求转发到pages/manager/book_manager.jsp页面.
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }
}
