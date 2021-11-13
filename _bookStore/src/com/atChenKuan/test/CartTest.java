package com.atChenKuan.test;

import com.atChenKuan.pojo.Cart;
import com.atChenKuan.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author 陈宽
 * @create 2021-05-07 16:23
 * @description
 */
public class CartTest {

    @Test
    public void addItem() {
        Cart cart = new Cart();

        cart.addItem(new CartItem(1,"java从入门到放弃",13,new BigDecimal(1000),new BigDecimal(100)));
        cart.addItem(new CartItem(2,"数据结构",12,new BigDecimal(900),new BigDecimal(900)));
        cart.addItem(new CartItem(3,"C语言编程",11,new BigDecimal(800),new BigDecimal(800)));
        System.out.println(cart);
    }

    @Test
    public void delete() {
        Cart cart = new Cart();

        cart.addItem(new CartItem(1,"java从入门到放弃",13,new BigDecimal(1000),new BigDecimal(100)));
        cart.addItem(new CartItem(2,"数据结构",12,new BigDecimal(900),new BigDecimal(900)));
        cart.addItem(new CartItem(3,"C语言编程",11,new BigDecimal(800),new BigDecimal(800)));

        cart.deleteItem(1);
        System.out.println(cart);
    }

    @Test
    public void clear() {
        Cart cart = new Cart();

        cart.addItem(new CartItem(1,"java从入门到放弃",13,new BigDecimal(1000),new BigDecimal(100)));
        cart.addItem(new CartItem(2,"数据结构",12,new BigDecimal(900),new BigDecimal(900)));
        cart.addItem(new CartItem(3,"C语言编程",11,new BigDecimal(800),new BigDecimal(800)));

        cart.clear();
        System.out.println(cart);
    }

    @Test
    public void updateCount() {
        Cart cart = new Cart();

        cart.addItem(new CartItem(1,"java从入门到放弃",13,new BigDecimal(1000),new BigDecimal(100)));
        cart.addItem(new CartItem(2,"数据结构",12,new BigDecimal(900),new BigDecimal(900)));
        cart.addItem(new CartItem(3,"C语言编程",11,new BigDecimal(800),new BigDecimal(800)));

        cart.updateCount(1,100);
        System.out.println(cart);
    }
}