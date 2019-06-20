package com.qf.j1902.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限表信息
 */
@Data
public class Permission {
    private Integer id;

    private Integer pid;

    private String name;

    private String icon;

    private String url;

    private boolean open = true;

    private boolean checked ;

    private int level ;

    private List<Permission> children = new ArrayList<>();
}
