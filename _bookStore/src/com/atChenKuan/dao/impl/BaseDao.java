package com.atChenKuan.dao.impl;

import com.atChenKuan.utils.dbcpUtils;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author 陈宽
 * @create 2021-02-16 16:00
 * @description  BeanDao，用于实现 数据库的操作，根据实际操作来实现需求(大概需求，不是具体需求，具体需求留给具体类去实现)
 *               数据库实现的功能无非就是增删改查，本类就写增删改查的各种情况，不去写具体sql语句。
 *     2021年5月12日13:31:48
 *     把所有的finally都注释掉了，因为新增加了事务功能，所有的数据库连接关闭都必须在事务完成后关闭
 *     如果在BaseDao中出现了数据库操作异常，就代表有非法操作，这个时候一系列的创建订单...创建购物车操作就不能生效，所以必须向外抛异常
 *     使用的是bdbutils.jar包，该包是对数据库增删改查的封装操作，不用繁琐的jdbc
 */
public abstract class BaseDao {
    //使用DButils操作数据库
    private QueryRunner queryRunner = new QueryRunner();

    /**
     * update()方法用来执行 insert，update，delete
     * @param sql  sql语句
     * @param args  可变长参数
     * @return 返回-1，则执行失败，返回其他数字表示执行成功影响的行数
     */
    public int update(String sql,Object ... args){
        //打印该方法所在线程的名字
        Connection conn = dbcpUtils.getConn();
        try {
            return queryRunner.update(conn,sql,args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            //如果数据库操作出现异常，就向外抛异常
            throw new RuntimeException(throwables);
        }
//        finally {
//            dbcpUtils.closeConn(conn);
//        }
    }

    /**
     * 查询返回一个javaBean的sql语句
     * @param type  返回的java类型
     * @param sql   执行的sql语句
     * @param args  sql对应的参数值
     * @param <T>   返回的类型的泛型
     * @return   返回-1则执行失败，其他数字则为成功行数
     */
    public <T> T queryForOne(Class<T> type,String sql,Object ... args){
        Connection conn = dbcpUtils.getConn();
        try {
            return queryRunner.query(conn,sql,new BeanHandler<T>(type),args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            //如果数据库操作出现异常，就向外抛异常
            throw new RuntimeException(throwables);
        }
//        finally {
//            dbcpUtils.closeConn(conn);
//        }
    }

    /**
     * 查询返回多个javaBean的sql语句
     * @param type  返回的java类型
     * @param sql   执行的sql语句
     * @param args  sql对应的参数值
     * @param <T>  返回的类型的泛型
     * @return     返回-1则执行失败，其他数字则为成功行数
     */
    public <T> List<T> queryForList(Class<T> type, String sql, Object ... args){
        Connection conn = dbcpUtils.getConn();
        try {
            return queryRunner.query(conn,sql,new BeanListHandler<>(type),args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            //如果数据库操作出现异常，就向外抛异常
            throw new RuntimeException(throwables);
        }
//        finally {
//            dbcpUtils.closeConn(conn);
//        }
    }

    /**
     * 执行返回一行一列的sql语句
     * @param sql
     * @param args  sql对应的参数值
     * @return   返回-1则执行失败，其他数字则为成功行数
     */
    public Object queryForSingleValue(String sql, Object ... args){
        Connection conn = dbcpUtils.getConn();
        try {
            return queryRunner.query(conn,sql,new ScalarHandler(),args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            //如果数据库操作出现异常，就向外抛异常
            throw new RuntimeException(throwables);
        }
//        finally {
//            dbcpUtils.closeConn(conn);
//        }
    }

}
