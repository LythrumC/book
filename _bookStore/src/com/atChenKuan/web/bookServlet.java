package com.atChenKuan.web;

import com.atChenKuan.dao.BookDao;
import com.atChenKuan.dao.impl.BookDaoImpl;
import com.atChenKuan.pojo.Book;
import com.atChenKuan.pojo.Page;
import com.atChenKuan.service.BookService;
import com.atChenKuan.service.impl.BookServiceImpl;
import com.atChenKuan.utils.webUtils;
import com.atChenKuan.web.baseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author 陈宽
 * @create 2021-03-02 15:44
 * @description
 */
public class bookServlet extends baseServlet {
    BookService bookService = new BookServiceImpl();

    /**
     * 处理分页功能
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("进入page方法");
        //1.获取请求参数pageNo和pageSize
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
         *   就把URL地址封装成Pa   ge对象后，再在自己对应的page()方法中将地址传进去
         *   这样就不用重复在jsp页面中写代码
         * */
        page.setUrl("manager/bookServlet?action=page");
//        System.out.println(page.getUrl());
        //3.保存page对象到Request域中
        req.setAttribute("page", page);
        //4.请求转发到/pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }

    /**
     * 从jsp页面获取图书信息，方便回传
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void getbook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取从jsp页面返回的图书id
        int id = webUtils.parseInt(req.getParameter("id"), 0);
        //2.利用DAO从数据库查询符合该 id的图书信息
        Book book = bookService.queryBookById(id);
        //3.将book信息保存到request域中
        req.setAttribute("book", book);
        //4.请求转发到 book_edit.jsp页面，把要显示的数据传过去
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req, resp);
    }


    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //得到由 add -> bookedit 传回来的pageNo(pageTotal)
        //在jsp页面 book_manager.jsp中有代码 如果超过最后一页，就显示最后一页。不用担心溢出问题
        int pageNo = webUtils.parseInt(req.getParameter("pageNo"), 0);
        pageNo += 1;
//        System.out.println("进入add方法");
        //1.封装从jsp页面得到的数据
        Book book = webUtils.copyParamToBean(req.getParameterMap(), new Book());
//        Map<String,String[]> map = req.getParameterMap();
//           for(Map.Entry<String,String[]> entry : map.entrySet()){
//            System.out.println(entry.getKey() + "=" + Arrays.asList(entry.getValue()));}
        //2.添加到数据库
        bookService.addBook(book);
        //3.跳转到jsp页面(bool_manager)
        /*req.getRequestDispatcher("/manager/bookServlet?action=list").forward(req,resp);
         * 是错误的，会引起表单重复提交bug，按一次F5会再添加一次相同数据
         * 因为request是一次提交请求，F5刷新后会再次执行 最后一次 执行的方法
         *例：  request是一次请求， 请求流程是  ①add->list, F5刷新会重复这个流程，所以会重复添加数据
         *
         * 而response是2次请求，不会重复提交数据,请求流程是   ①add  ->  ②list(即最后一次执行的方法)
         * */

        //  请求重定向的第一个 斜杠代表端口号，故要在前面还要加上工程名
        //定位到list遍历后，list会跳转到book_manager.jsp页面
//        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=list");
        //page分页后更新此段代码                                              此处由list -> page
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获得需要删除图书的ID
        String id = req.getParameter("id");
        //2.值的类型转换，然后把参数传进delete方法
        int i = webUtils.parseInt(id, 0);
        bookService.deleteBook(i);
        //3.重定向，刷新最新的数据
//        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
        //分页模型后更新                                                       此处由list -> page
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.把book_edit/.jsp修改页面的参数封装成新的 book对象
        Book book = webUtils.copyParamToBean(req.getParameterMap(), new Book());
        //2.通过Dao执行update操作
        bookService.updateBook(book);
        //3.重定向到book_manager中，并遍历数据
//        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=list&pageNo=" + req.getParameter("pageNo"));
        //分页模型后更新                                                       此处由list -> page
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    /**
     * 查询所有图书信息
     * //2021年3月18日14:44:24  在实现分页后，由add，update，delete跳转而来的list出现问题，后续改进将删除此注释
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.查询所有图书
        List<Book> books = bookService.queryBooks();
        System.out.println(books);
        //2.使用request域封装
        req.setAttribute("books", books);
        //3.发送数据到book_manager
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }

}
