package com.atChenKuan.test;

import com.atChenKuan.dao.BookDao;
import com.atChenKuan.dao.impl.BaseDao;
import com.atChenKuan.dao.impl.BookDaoImpl;
import com.atChenKuan.pojo.Book;
import com.atChenKuan.pojo.Page;
import com.atChenKuan.service.BookService;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;


/**
 * @author 陈宽
 * @create 2021-03-01 23:14
 * @description
 */
public class BookDaoImplTest {
    BookDao bookDao = new BookDaoImpl();
    @Test
    public void addBook() {
       bookDao.addBook(new Book(null,"三碗不过岗", new BigDecimal(126),"武松",2565,895,"static/img/default.jpg"));
    }

    @Test
    public void deleteBook() {
       bookDao.deleteBook(18);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(18,"三碗不过岗改编版",new BigDecimal(127),"武松",2565,895,"static/img/default.jpg"));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookDao.queryBookById(12));
    }

    @Test
    public void queryBooks() {
        for (Book queryBook : bookDao.queryBooks()) {
            System.out.println(queryBook);
        }
    }

    @Test
    public void queryForPageTotalCount() {
        System.out.println(bookDao.queryForPageTotalCount());
    }

    @Test
    public void queryForPageItems() {
        for (Book book : bookDao.queryForPageItems(0, Page.PAGE_SIZE)) {
            System.out.println(book);
        }
    }

    @Test
    public void queryForPageTotalCountByPrice() {
        System.out.println(bookDao.queryForPageTotalCountByPrice(200,600));
    }

    @Test
    public void queryForPageItemsByPrice() {
        for (Book book : bookDao.queryForPageItemsByPrice(0, Page.PAGE_SIZE,200,600)) {
            System.out.println(book);
        }
    }


    @Test
    public void queryForPageTotalCountByKeyName(){
        System.out.println(bookDao.queryForPageTotalCountByKeyName("笔记本"));
    }

    @Test
    public void queryForPageItemsByKeyName(){
        for (Book book : bookDao.queryForPageItemsByKeyName(0, Page.PAGE_SIZE,"笔记本")) {
            System.out.println(book);
        }
    }



}