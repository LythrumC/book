package com.atChenKuan.service;

import com.atChenKuan.dao.UserDao;
import com.atChenKuan.dao.impl.UserDaoImpl;
import com.atChenKuan.pojo.User;

/**
 * @author 陈宽
 * @create 2021-02-16 21:17
 * @description  service业务层，接口的作用就是提前写好要实现的功能，然后留给子类去调用增删改查(BaseDao)方法去具体实现
 *               在页面要实现注册，登录，和查重功能
 */
public interface UserService {
    /**
     * 注册
     * @param user
     */
    public void registUser(User user);

    /**
     * 登录
     * @param user
     * @return
     */
    public User login(User user);

    /**
     * 检查用户名是否可用
     * @param username
     * @return  返回true表示用户名已经存在；返回false表示可用
     */
    public boolean existsUsername(String username);

    /**
     * 查询该用户是否是系统管理员admin
     * @param usename
     * @return
     */
    public boolean existsUsernameVip(String username);
}
