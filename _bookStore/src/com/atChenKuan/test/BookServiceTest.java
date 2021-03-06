package com.atChenKuan.test;

import com.atChenKuan.dao.BookDao;
import com.atChenKuan.dao.impl.BookDaoImpl;
import com.atChenKuan.pojo.Book;
import com.atChenKuan.pojo.Page;
import com.atChenKuan.service.BookService;
import com.atChenKuan.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author 陈宽
 * @create 2021-03-02 14:22
 * @description
 */
public class BookServiceTest {
    private BookService bookService = new BookServiceImpl();

    @Test
    public void addBook() {
        bookService.addBook(new Book(null,"葫芦娃七兄弟",new BigDecimal(71.25),"蝎子精",48158,18528,null));
    }

    @Test
    public void deleteBook() {
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(21,"葫芦娃八兄弟",new BigDecimal(71),"蝎子精",48158,18582,null));
    }

    @Test
    public void queryBookById() {
       System.out.println(bookService.queryBookById(21));
    }

    @Test
    public void queryBooks() {
        for (Book queryBook : bookService.queryBooks()) {
            System.out.println(queryBook);
        }
    }

    @Test
    public void page(){
        System.out.println(bookService.page(1,Page.PAGE_SIZE));
    }

    @Test
    public void pageByPrice(){
        System.out.println(bookService.pageByPrice(1,Page.PAGE_SIZE,200,600));
    }
}