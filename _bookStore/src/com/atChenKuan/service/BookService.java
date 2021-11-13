package com.atChenKuan.service;

import com.atChenKuan.pojo.Book;
import com.atChenKuan.pojo.Page;

import java.util.List;

/**
 * @author 陈宽
 * @create 2021-03-02 14:16
 * @description  Service层中对图书信息的增删改查，依赖于Dao层。
 */
public interface BookService {
    /**
     * 增加图书信息
     * @param book
     */
    public void addBook(Book book);

    /**
     * 删除图书信息
     * @param id
     */
    public void deleteBook(Integer id);

    /**
     * 更新图书信息
     * @param book
     */
    public void updateBook(Book book);

    /**
     * 根据ID查询图书信息
     * @param id
     * @return
     */
    public Book queryBookById(Integer id);

    /**
     * 查询所有图书信息
     * @return
     */
    public List<Book> queryBooks();

    /**
     * 翻页查询图书信息
     * @param pageNo
     * @param pageSize
     * @return
     */
    Page<Book> page(int pageNo, int pageSize);

    /**
     *
     * @param pageNo  页码
     * @param pageSize  每一页显示多少数据
     * @return
     */
    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);

    Page<Book> pageByKeyName(int pageSize, int pageNo, String name);
}
