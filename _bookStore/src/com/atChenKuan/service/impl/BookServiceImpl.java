package com.atChenKuan.service.impl;

import com.atChenKuan.dao.BookDao;
import com.atChenKuan.dao.impl.BookDaoImpl;
import com.atChenKuan.pojo.Book;
import com.atChenKuan.pojo.Page;
import com.atChenKuan.service.BookService;

import java.util.List;

/**
 * @author 陈宽
 * @create 2021-03-02 14:18
 * @description  Service层中对图书信息的增删改查的具体实现，依赖于Dao层
 */
public class BookServiceImpl implements BookService {
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBook(Integer id) {
        bookDao.deleteBook(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    /**
     * 核心是给page对象的属性一一赋值
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page<>();
        //设置每页显示的数量
        page.setPageSize(pageSize);
        //求总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        //设置总记录数
        page.setPageTotalCount(pageTotalCount);
        //求总页码数
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize >0){
            pageTotal++;
        }
        //设置总页码
        page.setPageTotal(pageTotal);
        /*
        * 设置页码边界,当用户输入的页码不符合标准时，给他校准
        * 小于1的页码，自动校准为1.  大于最大页码的页码，给他校准为最大页码
        * 问题：每一个分页都要做一次边界，这样非常麻烦。  解决方案是把页码边距的校准放在java bean中
        */
//        if(pageNo <  0){
//            page.setPageNo(1);
//        }
//        if(pageNo > pageTotal){
//            page.setPageTotal(pageTotal);
//        }

        //设置当前页码
        page.setPageNo(pageNo);
        //求当前页数据的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;
        //求当前页数据
        List<Book> items = bookDao.queryForPageItems(begin,pageSize);
        //设置当前页数据
        page.setItems(items);
        //返回page对象
        return page;
    }

    /***
     *
     * @param pageNo  页码
     * @param pageSize  每一页显示多少数据
     * @return
     */
    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> page = new Page<>();
        //设置每页显示的数量
        page.setPageSize(pageSize);
        //按照价格区间min，max求总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCountByPrice(min,max);
        //设置总记录数
        page.setPageTotalCount(pageTotalCount);
        //求总页码数
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize >0){
            pageTotal++;
        }
        //设置总页码
        page.setPageTotal(pageTotal);
        /*
         * 设置页码边界,当用户输入的页码不符合标准时，给他校准
         * 小于1的页码，自动校准为1.  大于最大页码的页码，给他校准为最大页码
         * 问题：每一个分页都要做一次边界，这样非常麻烦。  解决方案是把页码边距的校准放在java bean中
         */
//        if(pageNo <  0){
//            page.setPageNo(1);
//        }
//        if(pageNo > pageTotal){
//            page.setPageTotal(pageTotal);
//        }

        //设置当前页码
        page.setPageNo(pageNo);
        //求当前页数据的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;
        //按照价格区间求当前页数据
        List<Book> items = bookDao.queryForPageItemsByPrice(begin,pageSize,min,max);
        //设置当前页数据
        page.setItems(items);
        //返回page对象
        return page;
    }

    /**
     * 按照关键字分页搜索
     * @param name 书籍关键字
     * @return
     */
    @Override
    public Page<Book> pageByKeyName(int pageSize, int pageNo, String name) {
        Page<Book> page = new Page<>();
        //设置每页显示的数量
        page.setPageSize(pageSize);
        //按照价格区间min，max求总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCountByKeyName(name);
        //设置总记录数
        page.setPageTotalCount(pageTotalCount);
        //求总页码数
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize >0){
            pageTotal++;
        }
        //设置总页码
        page.setPageTotal(pageTotal);
        /*
         * 设置页码边界,当用户输入的页码不符合标准时，给他校准
         * 小于1的页码，自动校准为1.  大于最大页码的页码，给他校准为最大页码
         * 问题：每一个分页都要做一次边界，这样非常麻烦。  解决方案是把页码边距的校准放在java bean中
         */
//        if(pageNo <  0){
//            page.setPageNo(1);
//        }
//        if(pageNo > pageTotal){
//            page.setPageTotal(pageTotal);
//        }

        //设置当前页码
        page.setPageNo(pageNo);
        //求当前页数据的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;
        //按照价格区间求当前页数据
        List<Book> items = bookDao.queryForPageItemsByKeyName(begin,pageSize,name);
        //设置当前页数据
        page.setItems(items);
        //返回page对象
        return page;
    }
}
