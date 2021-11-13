package com.atChenKuan.pojo;

/**
 * @author 陈宽
 * @create 2021-06-03 10:37
 * @description
 */
public class Vip {
    public Integer id;
    public String vipid;
    public String username;
    public String password;
    public String email;

    public Vip() {
    }

    public Vip(Integer id, String vipid, String username, String password, String email) {
        this.id = id;
        this.vipid = vipid;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVipid() {
        return vipid;
    }

    public void setVipid(String vipid) {
        this.vipid = vipid;
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
        return "Vip{" +
                "id=" + id +
                ", vipid='" + vipid + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
