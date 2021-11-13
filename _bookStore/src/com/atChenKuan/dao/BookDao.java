package com.atChenKuan.dao;

import com.atChenKuan.pojo.Book;

import java.util.List;

/**
 * @author 陈宽
 * @create 2021-03-01 22:50
 * @description  在数据库中对图书信息进行增删改查的具体操作
 */
public interface BookDao {

    /**
     * 增加图书信息
     * @param book
     * @return
     */
    public int addBook(com.atChenKuan.pojo.Book book);

    /**
     * 删除图书信息
     * @param id
     * @return
     */
    public int deleteBook(Integer id);

    /**
     * 更新图书信息
     * @param book
     * @return
     */
    public int updateBook(com.atChenKuan.pojo.Book book);

    /**
     * 根据图书ID查询图书
     * @param id
     * @return
     */
    public com.atChenKuan.pojo.Book queryBookById(Integer id);

    /**
     * 返回所有图书信息
     * @return
     */
    public List<Book> queryBooks();

    public Integer queryForPageTotalCount();

    public List<Book> queryForPageItems(int begin, int pageSize);

    /**
     *
     * @param min 最小价格
     * @param max 最大价格
     * @return  价格区间的所有数据
     */
    Integer queryForPageTotalCountByPrice(int min, int max);

    /**
     *   在原来分页的基础上，增加价格区间的限制。
     * @param begin    数据开始的下标
     * @param pageSize 固定回显的数据数量(每页)
     * @param min 最小价格
     * @param max 最大价格
     * @return 按照价格区间分好页的数据
     */
    List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max);

    /**
     * 根据关键字，查询数据中包含关键字的书籍的个数(模糊查询)
     * @param name
     * @return
     */
    Integer queryForPageTotalCountByKeyName(String name);

    /**
     * 根据关键字，查询数据中包含关键字的书籍的具体信息
     * @param begin
     * @param pageSize
     * @param name
     * @return
     */
    List<Book> queryForPageItemsByKeyName(int begin, int pageSize, String name);
}
