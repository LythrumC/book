package com.atChenKuan.dao;

import com.atChenKuan.pojo.User;
import com.atChenKuan.pojo.Vip;

/**
 * @author 陈宽
 * @create 2021-02-16 16:37
 * @description  UserDao接口，里面的方法根据具体实现的功能来定义,主要功能是和数据库交互
 *               本类就是根据业务需求来写具体要做的功能的接口，再在子类中通过调用增删改查方法去具体实现
 *               使用查询用户名，用户名和密码，插入信息，对数据库进行操作
 */
public interface UserDao {
    /**
     * 根据用户名在数据中查找用户
     * @param name  用户名
     * @return  如果返回null，则没有这个用户；反之亦然
     */
    public User queryUserByUsername(String name);

    /**
     * 根据用户名和密码查找用户
     * @param name
     * @param password
     * @return 如果返回null，则用户名或密码错误，反之亦然
     */
    public User queryUserByUsernameAndPassword(String name,String password);

    /**
     * 保存用户信息
     * @param user
     * @return
     */
    public int saveUser (User user);

    /**
     * 在数据库中，查找VIP表
     * @param usernane
     * @return
     */
    public Vip queryUserByUsernameVip(String username);


}

