package com.qf.j1902.controller;

import com.qf.j1902.pojo.Permission;
import com.qf.j1902.service.PermissionService;
import com.qf.j1902.utils.AjaxResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/permission")
@RequiresPermissions(value = {"许可维护"})
public class PermissionController extends BaseController {
    @Autowired
    private PermissionService permissionService;
    @RequestMapping("/index")
    public String index(){
        return "/permission/index";
    }
    @RequestMapping("/toAdd")
    public String toAdd(){
        return "/permission/add";
    }
    @ResponseBody
    @RequestMapping("/doAdd")
    public Object doAdd(Permission permission){
        AjaxResult result = new AjaxResult();
        try {
            int count = permissionService.savePermission(permission);
            result.setSuccess(count==1);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("保存数据失败");
        }
        return result;
    }
    @RequestMapping("/toUpdate")
    public String toUpdate(Integer id,Map map){
        Permission permission = permissionService.getPermissionById(id);
        map.put("permission",permission);
        return "/permission/update";
    }
    @ResponseBody
    @RequestMapping("/doUpdate")
    public Object toUpdate(Permission permission){
        AjaxResult result = new AjaxResult();
        try {
            int count = permissionService.updatePermission(permission);
            result.setSuccess(count==1);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("修改失败");
        }
        return result;
    }
    @ResponseBody
    @RequestMapping("/loadData")
    public Object loadData(){
        AjaxResult result = new AjaxResult();
        try {

            List<Permission> root = new ArrayList<>();

            List<Permission> childredPermissons =  permissionService.queryAllPermission();
            Map<Integer,Permission> map = new HashMap<>();//100

            for (Permission innerpermission : childredPermissons) {
                map.put(innerpermission.getId(), innerpermission);
            }
            for (Permission permission : childredPermissons){
//                通过子查父
//                子菜单
                Permission child = permission;
                if (child.getPid() == 0){
                    root.add(permission);
                }else {
//                    父节点
                    Permission parent = map.get(child.getPid());
                    parent.getChildren().add(child);
                }
            }
            result.setSuccess(true);
            result.setData(root);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("加载许可树数据失败!");
        }


        return result ;
    }
}
