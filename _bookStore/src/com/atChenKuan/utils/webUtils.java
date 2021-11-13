package com.atChenKuan.utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;

/**
 * @author 陈宽
 * @create 2021-02-28 16:52
 * @description  BeanUtils封装参数
 *    HttpServletRequest
 *    Dao层
 *    Service层
 *    web层
 *    耦合度低
 *    beanUtils封装参数的核心是通过反射，找到javaBean的 set()方法，将对应的参数写进去
 *    且参数名字必须和 javaBean里的参数名字相同！！！
 *
 */
public class webUtils {

    /**
     * 把Map中的值注入到对应的JavaBean属性中。
     * @param value
     * @param bean
     * @param <T>
     * @return bean对象
     */
                                        /**
                                         * 这里写Map类型比写 HttpServletRequest好
                                         * 不是所有的类都有HttpServletRequest的API
                                         * 这样写耦合度低，代码质量好
                                         * */
    public static <T> T copyParamToBean(Map value, T bean){
        try {
            //注入的属性本质是Map键值对
//            System.out.println("注入前" + value.toString());
            BeanUtils.populate(bean,value);  //把参数注入给对象
//            Map<String,String[]> map = value;
//           for(Map.Entry<String,String[]> entry : map.entrySet()){
//            System.out.println(entry.getKey() + "=" + Arrays.asList(entry.getValue()));
//        }

//           System.out.println(bean.toString());
//            System.out.println("注入后" + value.toString());
         }catch(Exception e) { System.out.println("注入失败"); };

        return bean;
    }

    /**
     * 值类型的转换，转换成Integer
     * @param id
     * @return 返回0则转换失败，其他值则转换成功
     */
    public static int parseInt(String id,int defaultValue){
        try {
            int i = Integer.parseInt(id);
            return i;
        } catch (Exception e) {
//            e.printStackTrace();
        }
        //转换失败返回默认值
        return defaultValue;
    }
}
