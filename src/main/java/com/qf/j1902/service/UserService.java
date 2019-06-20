package com.qf.j1902.service;

import com.qf.j1902.pojo.Permission;
import com.qf.j1902.pojo.Role;
import com.qf.j1902.pojo.User;
import com.qf.j1902.utils.Data;
import com.qf.j1902.utils.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface UserService {
//    根据登录用户名查询用户信息
    User selectUserByLoginacct(String loginacct);

    User queryUserlogin(HashMap<String, Object> paramMap);

    List<Permission> queryPermissionByUserid(Integer id);

    Page queryPage(Map paramMap);

    int saveUser(User user);

    User getUserById(Integer id);

    int updateUser(User user);

    int deleteUser(Integer id);

    int saveUserRoleRelationship(Integer userid, Data data);

    int deleteUserRoleRelationship(Integer userid, Data data);

    List<Role> querAllRole();

    List<Integer> queryRoleByUserid(Integer id);

    int deleteBatchUserByVO(Data data);
}
