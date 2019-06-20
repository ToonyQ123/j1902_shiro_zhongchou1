package com.qf.j1902.mapper;

import com.qf.j1902.pojo.Permission;
import com.qf.j1902.pojo.Role;
import com.qf.j1902.pojo.User;
import com.qf.j1902.utils.Data;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Mapper
public interface UserMapper {
//    根据用户登录名查询用户信息
     User findUserByLoginAcct(String loginacct);
//    根据用户ID查询所拥有的权限
    List<Permission> queryPermissionByUserid(Integer id);
//    根据map集合查询用户信息
    User queryUserlogin(HashMap<String, Object> paramMap);

    List<User> queryList(Map paramMap);

    Integer queryCount(Map paramMap);

    int insert(User user);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(User user);

    int deleteByPrimaryKey(Integer id);

    int deleteBatchUserByVO(@Param("userList") List<User> userList);

    List<Role> querAllRole();

    List<Integer> queryRoleByUserid(Integer id);

    int saveUserRoleRelationship(@Param("userid") Integer userid,@Param("data") Data data);

    int deleteUserRoleRelationship(@Param("userid") Integer userid,@Param("data")  Data data);

}
