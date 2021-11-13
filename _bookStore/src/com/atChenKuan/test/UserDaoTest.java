package com.atChenKuan.test;

import com.atChenKuan.dao.UserDao;
import com.atChenKuan.dao.impl.UserDaoImpl;
import com.atChenKuan.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author 陈宽
 * @create 2021-02-16 17:15
 * @description
 */
public class UserDaoTest {
    UserDao userDao = new UserDaoImpl();

    @org.junit.Test
    public void queryUserByUsername() {
        if(userDao.queryUserByUsername("admin") == null){
            System.out.println("用户名可用");
        }else{
            System.out.println("用户名已经存在");
        }
    }

    @org.junit.Test
    public void queryUserByUsernameAndPassword() {
        if (userDao.queryUserByUsernameAndPassword("admin","admin") == null){
            System.out.println("用户名或密码错误");
        }else{
            System.out.println("查询成功");
        }
    }

    @org.junit.Test
    public void saveUser() {
        System.out.println(userDao.saveUser(new User("ck","123456","370488625@qq.com")));
    }

    @Test
    public void queryUserByUsernameVip(){
        System.out.println(userDao.queryUserByUsernameVip("admin"));
    }
}