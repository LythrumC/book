package com.atChenKuan.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 陈宽
 * @create 2021-05-07 14:56
 * @description  购物车模块，所有的购物项增删改在这里完成，依托于Session，不用数据库
 */
public class Cart {
         //这两个变量没有必要用set方法，get方法里面直接返回总价和总数量即可，所以他们最好是局部变量，不占用多的内存
//    private Integer totalCount;  //购物车的物品总数
//    private BigDecimal totalPrice;  //购物车物品总价
    private Map<Integer,CartItem> items = new LinkedHashMap<>();  //购物车具体物品项目

    public Cart() {
    }

    public Cart(Integer totalCount, BigDecimal totalPrice, Map<Integer, CartItem> items) {
//        this.totalCount = totalCount;
//        this.totalPrice = totalPrice;
        this.items = items;
    }

    /**
     * 物品项的添加
     * @param cartItem
     */
    public void addItem(CartItem cartItem){
        //添加一个物品，首先要查在列表里面有没有这个物品，有则在原来的基础上增加，没有就新增加对象
        CartItem item = items.get(cartItem.getId());
        if(item == null){
            items.put(cartItem.getId(),cartItem); //cartItem的ID在Map中必定是唯一的
        }else{
            //如果存在，则在原来的数据中修改
            //这里为什么修改了对象的值，却没有重新put回去？   因为上面通过 items.get()取的是这个对象的地址，并不是这个对象本身
            item.setCount(item.getCount() + 1);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));  //更新总金额(单价*数量)
        }

    }


    /**
     * 物品项的删除
     * @param id
     */
    public void deleteItem(Integer id){
        items.remove(id);
    }

    /**
     * 清空购物车
     */
    public void clear(){
        items.clear();
    }

    /**
     * 更新物品项数量
     * @param id
     * @param count
     */
    public void updateCount(Integer id, Integer count){
        //先查看购物车中有没有该物品项，有才在原来的基础上修改
        CartItem cartItem = items.get(id);
        if(cartItem != null){
            cartItem.setCount(count);  //修改商品数量
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));  //更新总金额(单价*数量)
        }
    }


    /**
     *  每一个购物车有不同的商品对象，每一个商品对象都有不同的数量，该方法返回所有商品对象的总数量
     * @return 总数量
     */
    public Integer getTotalCount() {
        Integer totalCount = 0;

        //遍历所有商品对象，获取他们的count，最后相加
        for(Map.Entry<Integer,CartItem> entry : items.entrySet()){
            totalCount += entry.getValue().getCount();
        }
        return totalCount;
    }

    /**
     *  每一个购物车有不同的商品对象，每一个商品对象都有不同的价格，该方法返回所有商品对象的总价格
     * @return 总价格
     */
    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);

        //遍历所有商品对象，获取他们的count，最后相加
        for(Map.Entry<Integer,CartItem> entry : items.entrySet()){
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());  //遍历所有对象的总价，然后全部相加就是整个购物车的金额
        }
        return totalPrice;
    }


    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
