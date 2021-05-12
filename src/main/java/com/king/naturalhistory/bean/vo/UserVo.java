package com.king.naturalhistory.bean.vo;

import lombok.Data;

@Data
public class UserVo {
    private int userid;
    private String username;

    public UserVo(int userid, String username) {
        this.userid = userid;
        this.username = username;
    }
}
