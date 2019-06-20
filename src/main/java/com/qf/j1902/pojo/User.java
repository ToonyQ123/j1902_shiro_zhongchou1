package com.qf.j1902.pojo;

import lombok.Data;

/**
 * 用户表的信息
 */
@Data
public class User {
    private Integer id;

    private String loginacct;

    private String userpswd;

    private String username;

    private String email;

    private String createtime;
}
