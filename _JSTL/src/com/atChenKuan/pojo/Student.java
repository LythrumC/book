package com.atChenKuan.pojo;

/**
 * @author 陈宽
 * @create 2021-02-25 15:29
 * @description
 */
public class Student {
    private Integer id;
    private String username;
    private String password;
    private int age;
    private String numbers;

    public Student() {
    }

    public Student(Integer id, String username, String password, int age, String numbers) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.age = age;
        this.numbers = numbers;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }
}
