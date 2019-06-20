package com.qf.j1902.controller;

import com.qf.j1902.pojo.Permission;
import com.qf.j1902.pojo.Role;
import com.qf.j1902.service.PermissionService;
import com.qf.j1902.service.RoleService;
import com.qf.j1902.utils.AjaxResult;
import com.qf.j1902.utils.Data;
import com.qf.j1902.utils.Page;
import com.qf.j1902.utils.StringUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiresPermissions(value = {"角色维护"})
@RequestMapping("/role")
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;
    @RequestMapping("/index")
    public String index(){
        return "/role/index";
    }
    @RequestMapping("/add")
    public String add(){
        return "/role/add";
    }
    @RequestMapping("/assignPermission")
    public String assignPermission(){
        return "/role/assignPermission";
    }
    @ResponseBody
    @RequestMapping("/doAssignPermission")
    public Object doAssignPermission(Integer roleid, Data datas){
        AjaxResult result = new AjaxResult();
        try {
            int count = roleService.saveRolePermissionRelationship(roleid, datas);
            result.setSuccess(count==datas.getIds().size());
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
        }
        return result;
    }
//    异步请求树结构
    @ResponseBody
    @RequestMapping("/loadDataAsync")
    public Object loadDataAsync(Integer roleid){
        List<Permission> root = new ArrayList<>();

        List<Permission> childredPermissons =  permissionService.queryAllPermission();

        //根据角色id查询该角色之前所分配过的许可.
        List<Integer> permissonIdsForRoleid = permissionService.queryPermissionidsByRoleid(roleid);

        Map<Integer,Permission> map = new HashMap<>();//100

        for (Permission innerpermission : childredPermissons) {
            map.put(innerpermission.getId(), innerpermission);
            if(permissonIdsForRoleid.contains(innerpermission.getId())){
                innerpermission.setChecked(true);
            }
        }
        for (Permission permission : childredPermissons) { //100
            //通过子查找父
            //子菜单
            Permission child = permission ; //假设为子菜单
            if(child.getPid() == 0 ){
                root.add(permission);
            }else{
                //父节点
                Permission parent = map.get(child.getPid());
                parent.getChildren().add(child);
            }
        }

        return root ;
    }
//    修改页面
    @RequestMapping("/edit")
    public String edit(Integer id,Map<String,Object> map){
        Role role = roleService.getRole(id);
        map.put("role",role);
        return "/role/edit";
    }
//    处理修改数据
    @ResponseBody
    @RequestMapping("/doEdit")
    public Object doEdit(Role role){
        AjaxResult result = new AjaxResult();
        try {
            int count = roleService.updateRole(role);
            if (count == 1){
                result.setSuccess(true);
            }else {
                result.setSuccess(false);
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
        }
        return result;
    }
    @ResponseBody
    @RequestMapping("/doAdd")
    public Object doAdd(Role role) {
        AjaxResult result = new AjaxResult();

        try {
            roleService.saveRole(role);
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
        }

        return result;
    }

    /**
     * 异步分页查询
     * @param pageno
     * @param pagesize
     * @return
     */
    @ResponseBody
    @RequestMapping("/pageQuery")
    public Object pageQuery(String queryText,@RequestParam(required = false, defaultValue = "1") Integer pageno,
                            @RequestParam(required = false, defaultValue = "2") Integer pagesize){
        AjaxResult result = new AjaxResult();
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("pageno", pageno); // 空指针异常
            paramMap.put("pagesize", pagesize);

            if(StringUtil.isNotEmpty(queryText)){
                queryText = queryText.replaceAll("%", "\\\\%"); //斜线本身需要转译
                System.out.println("--------------"+queryText);
            }

            paramMap.put("queryText", queryText);

            // 分页查询数据
            Page<Role> rolePage = roleService.pageQuery(paramMap);

            result.setPage(rolePage);
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
        }
        return  result;
    }
}
