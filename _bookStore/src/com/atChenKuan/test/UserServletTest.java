package com.atChenKuan.test;

import com.atChenKuan.pojo.User;
import com.atChenKuan.service.UserService;
import com.atChenKuan.service.impl.UserServiceImpl;
import com.atChenKuan.web.UserServlet;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author 陈宽
 * @create 2021-02-28 15:55
 * @description  测试使用类反射调用方法
 */
public class UserServletTest {
    UserService userService = new UserServiceImpl();

    public void regist(){
        System.out.println("处理注册方法");
    }

    public void login(){
        System.out.println("处理登录方法");
    }

    public static void main(String[] args) {
        String action = "login";

        try {
            Method declaredMethod = UserServletTest.class.getDeclaredMethod(action);
            System.out.println(declaredMethod);

            declaredMethod.invoke(new UserServletTest());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void queryUserByUsernameVip(){
        System.out.println(userService.existsUsernameVip("admin"));
    }


}
