package com.atChenKuan;

/**
 * @author 陈宽
 * @create 2021-05-12 9:58
 * @description
 */

import java.util.Hashtable;
import java.util.Map;
import java.util.Random;

/**
 * 线程池的测试使用(为当前线程存储一个变量，一个ThreadLocal对象只能存储一个)
 * ThreadLocal的作用，他可以解决多线程的线程安全问题
 * ThreadLocal他可以给当前线程关联一个数据（数据可以使普通变量，可以是对象，也可以是数组，集合/////）
 *
 * ThreadLocal特点:
 *    1.ThreadLocal可以为当前线程关联一个数据(可以像Map一样存取数据，Key值为当前线程名)
 *    2.每一个ThreadLocal对象，只能为的去当前一个线程关联一个数据，如果要关联多个数据，就要使用多个ThreadLocal对象实例
 *    3.每个ThreadLocal对象实例定义的时候，一般都是Static类型
 *    4.ThreadLocal中保存数据，在线程销毁后，会由JVM虚拟自动释放
 */
public class ThreadLocalTest {
    //用来模拟ThreadLocal
//     public static Map<String,Object> data = new Hashtable<>();
     public static ThreadLocal<Object> threadLocal = new ThreadLocal<>();
     public static Random random = new Random();

     public static class Task implements Runnable{
         @Override
         public void run() {
             Integer i = random.nextInt(1000);
             //在Run方法中，随机生成一个变量(线程要关联的数据),然后以当前线程名来存取这个数据
             String name = Thread.currentThread().getName();  //当前线程名
             System.out.println("线程【"+ name +"】生成的随机数是:" + i);
//             data.put(name,i);
             threadLocal.set(i);  //直接调用set方法即可实现和模拟的Map.put()方法一样的功能

             try {
                 Thread.sleep(5000);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }

             new OrderService().onCreate();

//             Object o = data.get(name);
             Object o = threadLocal.get(); //因为每一个ThreadLocal对象只能存一个值，所以不需要给名字参数
             System.out.println("在线程【"+ name +"】快要结束时取出关联的数据时:" + o);
         }
     }

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(new ThreadLocalTest.Task()).start();
        }
    }



}
