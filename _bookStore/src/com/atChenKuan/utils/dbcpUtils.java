package com.atChenKuan.utils;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author 陈宽
 * @create 2021-02-16 15:41
 * @description dbcp数据库连接  dao持久层
 */
public class dbcpUtils {
    //创建保存数据库连接对象的线程池，用来保证所有的操作都基于一个Conn连接，便于处理异常和回滚事务。可以连接为一个map集合，只能存储一个对象
    private static ThreadLocal<Connection> conns = new ThreadLocal<>();
    private static DataSource dataSource = null;

    //静态代码块
    static {
        try {
            Properties properties = new Properties();
            //getResourceAsStream的默认路径是src
            InputStream resourceAsStream = dbcpUtils.class.getClassLoader().getResourceAsStream("dbcp.properties");
            properties.load(resourceAsStream);
            dataSource = BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
//            System.out.println("数据库连接失败");
        }
    }

    //拿取数据库连接
//    public static Connection getConn(){
//        Connection conn = null;
//        try {
//            if(conn == null){
//                conn = dataSource.getConnection();
//                return conn;
//            }
//        } catch (SQLException throwables) { System.out.println("获取连接错误"); }
//        return conn;
//    }

    //用线程池拿去数据库连接
    public static Connection getConn(){
        Connection conn = conns.get();
        if(conn == null){
            try {
                conn = dataSource.getConnection();
                //线程池保存这个连接
                conns.set(conn);
                //设置事务处理为手动提交
                conn.setAutoCommit(false);
            } catch (SQLException throwables) { throwables.printStackTrace(); }
        }
        return conn;
    }

    /**
     * 提交事物，并关闭连接
     */
    public static void commitAndClose(){
        Connection conn = conns.get();
        if(conn != null){
            try { conn.commit(); } catch (SQLException throwables) { throwables.printStackTrace(); }
            finally { try { conn.close(); } catch (SQLException throwables) { throwables.printStackTrace(); } }
        }
        //一定要执行remove()操作，因为Tomcat服务层底层使用的是线程池技术，不关闭会造成冲突错误
        conns.remove();
    }

    /**
     * 回滚事物，并关闭连接
     */
    public static void rollBackAndClose(){
        Connection conn = conns.get();
        if(conn != null){
            try { conn.rollback(); } catch (SQLException throwables) { throwables.printStackTrace(); }
            finally { try { conn.close(); } catch (SQLException throwables) { throwables.printStackTrace(); } }
        }
        //一定要执行remove()操作，因为Tomcat服务层底层使用的是线程池技术，不关闭会造成冲突错误
        conns.remove();
    }

    //关闭连接，返回数据库连接池
    public static void closeConn(Connection conn){
        try {
            if(conn != null){
                conn.close();
            }
        } catch (SQLException throwables) { System.out.println("关闭连接错误"); }
    }


}
