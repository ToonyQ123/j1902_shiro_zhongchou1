package com.qf.j1902.pojo;

import lombok.Data;

/**
 * 角色表和权限表关联表的信息
 */
@Data
public class RolePermission {
    private Integer id;

    private Integer roleid;

    private Integer permissionid;
}
