package com.atChenKuan.pojo;

/**
 * @author 陈宽
 * @create 2021-02-16 16:51
 * @description 数据库对应的JavaBean对象
 */
public class User {
    private int id;
    private String username;
    private String password;
    private String email;

    public User() {
    }

    public User( String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
