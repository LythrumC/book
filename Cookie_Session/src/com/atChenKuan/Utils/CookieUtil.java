package com.atChenKuan.Utils;

import javax.servlet.http.Cookie;

/**
 * @author 陈宽
 * @create 2021-04-24 15:30
 * @description  查找Cookies的工具类
 */
public class CookieUtil {

    /***
     * 传进cookie名字，找到响应的cookie
     * @param name  cookie名字
     * @param cookies  对应的cookie对象
     * @return  需要找到的cookie对象
     */
    public static Cookie findCookie(String name, Cookie[] cookies){
        for (Cookie cookie : cookies) {
            //如果没有传进来数据，就直接返回null
            if(cookies.length == 0 || name == null || cookie == null){
                return null;
            }
            //有数据就查找
            if(name.equals(cookie.getName())){
                return cookie;
            }
        }
        return null;
    }

}
