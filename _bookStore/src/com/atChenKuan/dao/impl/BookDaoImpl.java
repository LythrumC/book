package com.atChenKuan.dao.impl;

import com.atChenKuan.dao.BookDao;
import com.atChenKuan.pojo.Book;

import java.util.List;

/**
 * @author 陈宽
 * @create 2021-03-01 22:54
 * @description  数据库中对图书进行增删改查的具体实现
 */
public class BookDaoImpl extends BaseDao implements BookDao {

    @Override
    public int addBook(Book book) {
        String sql = "insert into t_book(name,price,author,sales,stock,imgPath) values(?,?,?,?,?,?)";
        return update(sql,book.getName(),book.getPrice(),book.getAuthor(),book.getSales(),book.getStock(),book.getimgPath());
    }

    @Override
    public int deleteBook(Integer id) {
        String sql = "delete from t_book where id = ?";
        return update(sql,id);
    }

    @Override
    public int updateBook(Book book) {
        String sql = "update t_book set name = ?, price = ?, author = ?, sales = ?, stock = ?, imgPath = ? where id = ?";
        return update(sql,book.getName(),book.getPrice(),book.getAuthor(),book.getSales(),book.getStock(),book.getimgPath(),book.getId());
    }

    @Override
    public Book queryBookById(Integer id) {
        String sql = "select id, name, author, price, sales, stock, imgPath from t_book where id = ?";
        return queryForOne(Book.class,sql,id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql = "select id,name,price,author,sales,stock, imgPath AS 'imgPath' from t_book";
        return queryForList(Book.class,sql);
    }

    /***
     * 求总数据
     * @return
     */
    @Override
    public Integer queryForPageTotalCount() {
        String sql = "select count(*) from t_book";
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();
    }

    @Override
    public List<Book> queryForPageItems(int begin, int pageSize) {
        String sql = "select id,name,price,author,sales,stock, imgPath AS 'imgPath' from t_book limit ?,?";
        return queryForList(Book.class,sql,begin,pageSize);
    }

    /**
     * 按照价格区间查询总数据
     * @param min 最小价格
     * @param max 最大价格
     * @return  count数据条目数量
     */
    @Override
    public Integer queryForPageTotalCountByPrice(int min, int max) {
        String sql = "select count(*) from t_book where price between ? and ?";
        Number count = (Number) queryForSingleValue(sql,min,max);
        return count.intValue();
    }

    /**
     * 按照价格区间返回当前页数据
     * @param begin  当前页起始页数
     * @param pageSize  当前页总页数
     * @param min   最小价格
     * @param max   最大价格
     * @return
     */
    @Override
    public List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max) {
        String sql = "select id,name,price,author,sales,stock, imgPath AS 'imgPath' from t_book where price between ? and ? order by price limit ?,?";
        return queryForList(Book.class,sql,min,max,begin,pageSize);
    }

    /**
     * 按关键字查找相关书籍的总数
     * @param name
     * @return  返回数量(%和?是同等级的) 百分号要转义 解决方案:https://blog.csdn.net/qq_35151346/article/details/79889210
     */
    @Override
    public Integer queryForPageTotalCountByKeyName(String name) {
        String sql = "select count(*) from t_book where name like \"%\"?\"%\"";
        Number count = (Number) queryForSingleValue(sql,name);
        return count.intValue();
    }

    /**
     * 查找关键字书籍的具体信息
     * @param begin 起始位置
     * @param pageSize 显示数量
     * @param name  关键字
     * @return
     */
    @Override
    public List<Book> queryForPageItemsByKeyName(int begin, int pageSize, String name) {
        String sql = "select id,name,price,author,sales,stock, imgPath AS 'imgPath' from t_book where name like \"%\"?\"%\" limit ?,?";
        return queryForList(Book.class,sql,name,begin,pageSize);
    }
}
