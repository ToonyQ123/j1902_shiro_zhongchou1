package com.qf.j1902.service.impl;

import com.qf.j1902.mapper.RoleMapper;
import com.qf.j1902.pojo.Role;
import com.qf.j1902.pojo.RolePermission;
import com.qf.j1902.service.RoleService;
import com.qf.j1902.utils.Data;
import com.qf.j1902.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public Page<Role> pageQuery(Map<String, Object> paramMap) {
        Page<Role> rolePage = new Page<Role>((Integer)paramMap.get("pageno"),(Integer)paramMap.get("pagesize"));

        paramMap.put("startIndex", rolePage.getStartIndex());

        List<Role> roles = roleMapper.pageQuery(paramMap);

        // 获取数据的总条数
        int count = roleMapper.queryCount(paramMap);
        rolePage.setData(roles);
        rolePage.setTotalsize(count);
        return rolePage;
    }

    @Override
    public int queryCount(Map<String, Object> paramMap) {
        return 0;
    }

    @Override
    public void saveRole(Role user) {

    }

    @Override
    public Role getRole(Integer id) {
        return roleMapper.getRole(id);
    }

    @Override
    public int updateRole(Role role) {
        return roleMapper.update(role);
    }

    @Override
    public int deleteRole(Integer uid) {
        return 0;
    }

    @Override
    public int batchDeleteRole(Integer[] uid) {
        return 0;
    }

    @Override
    public int batchDeleteRole(Data datas) {
        return 0;
    }

    @Override
    public List<Role> queryAllRole() {
        return null;
    }

    @Override
    public List<Integer> queryRoleidByUserid(Integer id) {
        return null;
    }

    @Override
    public void doAssignRoleByUserid(Integer userid, List<Integer> ids) {

    }

    @Override
    public void doUnAssignRoleByUserid(Integer userid, List<Integer> ids) {

    }

    @Override
    public int saveRolePermissionRelationship(Integer roleid, Data datas) {
        roleMapper.deleteRolePermissionRelationship(roleid);

        int totalCount = 0 ;
        List<Integer> ids = datas.getIds();
        for (Integer permissionid : ids) {
            RolePermission rp = new RolePermission();
            rp.setRoleid(roleid);
            rp.setPermissionid(permissionid);
            int count = roleMapper.insertRolePermission(rp);
            totalCount += count ;
        }
        return totalCount;
    }
}
