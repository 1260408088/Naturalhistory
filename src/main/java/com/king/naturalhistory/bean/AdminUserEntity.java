package com.king.naturalhistory.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "admin_user")
public class AdminUserEntity implements Serializable {
    private String uid;
    private String uname;
    private Integer usex;
    private String upic;
    private String uaccount;
    private String upassword;
    private Integer uperm;
    private String umsg;
    private String uemail;
    private String utel;
    private String UID;

    @Basic
    @Id
    @Column(name = "UID")
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "UNAME")
    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    @Basic
    @Column(name = "USEX")
    public Integer getUsex() {
        return usex;
    }

    public void setUsex(Integer usex) {
        this.usex = usex;
    }

    @Basic
    @Column(name = "UPIC")
    public String getUpic() {
        return upic;
    }

    public void setUpic(String upic) {
        this.upic = upic;
    }

    @Basic
    @Column(name = "UACCOUNT")
    public String getUaccount() {
        return uaccount;
    }

    public void setUaccount(String uaccount) {
        this.uaccount = uaccount;
    }

    @Basic
    @Column(name = "UPASSWORD")
    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    @Basic
    @Column(name = "UPERM")
    public Integer getUperm() {
        return uperm;
    }

    public void setUperm(Integer uperm) {
        this.uperm = uperm;
    }

    @Basic
    @Column(name = "UMSG")
    public String getUmsg() {
        return umsg;
    }

    public void setUmsg(String umsg) {
        this.umsg = umsg;
    }

    @Basic
    @Column(name = "UEMAIL")
    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }

    @Basic
    @Column(name = "UTEL")
    public String getUtel() {
        return utel;
    }

    public void setUtel(String utel) {
        this.utel = utel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminUserEntity that = (AdminUserEntity) o;
        return Objects.equals(uid, that.uid) &&
                Objects.equals(uname, that.uname) &&
                Objects.equals(usex, that.usex) &&
                Objects.equals(upic, that.upic) &&
                Objects.equals(uaccount, that.uaccount) &&
                Objects.equals(upassword, that.upassword) &&
                Objects.equals(uperm, that.uperm) &&
                Objects.equals(umsg, that.umsg) &&
                Objects.equals(uemail, that.uemail) &&
                Objects.equals(utel, that.utel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, uname, usex, upic, uaccount, upassword, uperm, umsg, uemail, utel);
    }
}
