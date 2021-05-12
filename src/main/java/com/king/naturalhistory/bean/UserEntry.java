package com.king.naturalhistory.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user")
@JsonSerialize()
public class UserEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userid;
    private String username;
    @JsonIgnore
    private String password;
    private String telphone;
    private String realname;
    private Date createtime;
    private Date updatetime;
    private Date lastlogintime;

    @Column(name = "userid")
    public int getUserid() {
        return userid;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    @Basic
    @Column(name = "telphone")
    public String getTelphone() {
        return telphone;
    }

    @Basic
    @Column(name = "realname")
    public String getRelname() {
        return realname;
    }

    @Basic
    @Column(name = "createtime")
    public Date getCreateTime() {
        return createtime;
    }

    @Basic
    @Column(name = "updatetime")
    public Date getUpdateTime() {
        return updatetime;
    }

    @Basic
    @Column(name = "lastlogintime")
    public Date getLastLoginTime() {
        return lastlogintime;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public void setRelname(String realname) {
        this.realname = realname;
    }

    public void setCreateTime(Date createTime) {
        this.createtime = createTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updatetime = updateTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastlogintime = lastLoginTime;
    }
}
