package com.qf.j1902.service;

import com.qf.j1902.pojo.Role;
import com.qf.j1902.utils.Data;
import com.qf.j1902.utils.Page;

import java.util.List;
import java.util.Map;

public interface RoleService {
    public Page<Role> pageQuery(Map<String, Object> paramMap);

    public int queryCount(Map<String, Object> paramMap);

    public void saveRole(Role role);

    public Role getRole(Integer id);

    public int updateRole(Role role);

    public int deleteRole(Integer uid);

    public int batchDeleteRole(Integer[] uid);

    public int batchDeleteRole(Data datas);

    public List<Role> queryAllRole();

    public List<Integer> queryRoleidByUserid(Integer id);

    public void doAssignRoleByUserid(Integer userid, List<Integer> ids);

    public void doUnAssignRoleByUserid(Integer userid, List<Integer> ids);

    public int saveRolePermissionRelationship(Integer roleid, Data datas);
}
