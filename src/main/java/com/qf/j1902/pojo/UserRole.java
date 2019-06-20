package com.qf.j1902.pojo;

import lombok.Data;

/**
 * 用户表和角色表的关联表信息
 */
@Data
public class UserRole {
    private Integer id;

    private Integer userid;

    private Integer roleid;
}
