package com.atChenKuan.service.impl;

import com.atChenKuan.dao.UserDao;
import com.atChenKuan.dao.impl.UserDaoImpl;
import com.atChenKuan.pojo.User;
import com.atChenKuan.service.UserService;

/**
 * @author 陈宽
 * @create 2021-02-16 21:22
 * @description   实现servlet业务层的功能，本质还是和数据库进行交互，所以要使用UserDapImpl
 */
public class UserServiceImpl implements UserService {
    //因为UserService也要和数据库交互，故要使用到UserDaoImpl
    UserDao userdao = new UserDaoImpl();

    @Override
    public void registUser(User user) {
        userdao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userdao.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
        if (userdao.queryUserByUsername(username) == null){
            //等于Null说明说明没查到，可以用
            return true;
        }else {
            //等于1说明查到了，用户名已经存在，不可用
            return false;
        }
    }

    @Override
    public boolean existsUsernameVip(String username) {
        if(userdao.queryUserByUsernameVip(username) == null){
            //等于Null说明没有查到信息，代表该用户不是VIP
            return false;
        }else{
            //查到了该用户，说明用户存在，他是VIP
            return true;
        }
    }
}
