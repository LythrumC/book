package com.atChenKuan.pojo;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author 陈宽
 * @create 2021-02-24 14:28
 * @description
 */
public class Person {
    private String name;
    private int age;
    private String[] Phones;
    private List<String> cities;
    private Map<String,Object> map;

    public Person() {
    }

    public Person(String name, String[] phones, List<String> cities, Map<String, Object> map) {
        this.name = name;
        Phones = phones;
        this.cities = cities;
        this.map = map;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getPhones() {
        return Phones;
    }

    public void setPhones(String[] phones) {
        Phones = phones;
    }

    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", Phones=" + Arrays.toString(Phones) +
                ", cities=" + cities +
                ", map=" + map +
                '}';
    }
}
