package com.atChenKuan.dao.impl;

import com.atChenKuan.dao.UserDao;
import com.atChenKuan.pojo.User;
import com.atChenKuan.pojo.Vip;

/**
 * @author 陈宽
 * @create 2021-02-16 16:58
 * @description   本质是对数据库的操作
 */
public class UserDaoImpl extends BaseDao implements UserDao {

    @Override
    public User queryUserByUsername(String username) {
        String sql = "select * from book_user where username = ?";
        return queryForOne(User.class, sql, username);
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql = "select * from book_user where username = ? and password = ?";
        return queryForOne(User.class, sql, username, password);
    }

    @Override
    public Vip queryUserByUsernameVip(String username) {
        String sql = "select * from vipuser where username = ?";
        return queryForOne(Vip.class, sql, username);
    }


    @Override
    public int saveUser(User user) {
        //language=Mysql
        String sql = "insert into book_user(username,password,email) values (?,?,?)";
        return update(sql, user.getUsername(), user.getPassword(), user.getEmail());
    }
}


