package com.qf.j1902.controller;

import com.qf.j1902.pojo.Role;
import com.qf.j1902.pojo.User;
import com.qf.j1902.service.UserService;
import com.qf.j1902.utils.AjaxResult;
import com.qf.j1902.utils.Data;
import com.qf.j1902.utils.Page;
import com.qf.j1902.utils.StringUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiresPermissions(value = {"用户维护"})
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String index(){
        return "/user/index";
    }
    //转到添加页面
    @RequestMapping("/toadd")
    public String toadd(){
        return "/user/add";
    }
    //分配角色
    @ResponseBody
    @RequestMapping("/doAssignRole")
    public Object doAssignRole(Integer userid,Data data){
        AjaxResult result = new AjaxResult();
        try {

            userService.saveUserRoleRelationship(userid,data);
            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
            e.printStackTrace();
            result.setMessage("分配角色数据失败!");
        }

        return result;
    }

    //取消分配角色
    @ResponseBody
    @RequestMapping("/doUnAssignRole")
    public Object doUnAssignRole(Integer userid,Data data){
        AjaxResult result = new AjaxResult();
        try {

            userService.deleteUserRoleRelationship(userid,data);
            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
            e.printStackTrace();
            result.setMessage("取消分配角色数据失败!");
        }

        return result;
    }


    //显示分配页面数据.
    @RequestMapping("/assignRole")
    public String assignRole(Integer id,Map map){

        List<Role> allListRole = userService.querAllRole();

        List<Integer> roleIds = userService.queryRoleByUserid(id);


        List<Role> leftRoleList = new ArrayList<>(); //未分配角色
        List<Role> rightRoleList = new ArrayList<>(); //已分配角色


        for(Role role : allListRole){

            if(roleIds.contains(role.getId())){
                rightRoleList.add(role);
            }else{
                leftRoleList.add(role);
            }

        }


        map.put("leftRoleList", leftRoleList);
        map.put("rightRoleList", rightRoleList);


        return "/user/assignrole";
    }
    //模糊查询
    @ResponseBody
    @RequestMapping("/doIndex")
    public Object index(@RequestParam(value = "pageno",required = false,defaultValue = "0") Integer pageno,
                        @RequestParam(value = "pagesize",required = false,defaultValue ="10" )Integer pagesize,
                        String queryText){
            AjaxResult result = new AjaxResult();
            try {
                Map paramMap = new HashMap();
                paramMap.put("pageno",pageno);
                paramMap.put("pagesize",pagesize);
                if (StringUtil.isNotEmpty(queryText)){
                    if (queryText.contains("%")){
                        queryText=queryText.replaceAll("%","\\\\%");
                    }
                    paramMap.put("queryText",queryText);
                }
                Page page = userService.queryPage(paramMap);
                result.setSuccess(true);
                result.setPage(page);
            }catch (Exception e){
                result.setSuccess(false);
                e.printStackTrace();
                result.setMessage("查询数据失败！！！！");
            }
            return result;
    }
    //添加数据
    @ResponseBody
    @RequestMapping("/doAdd")
    public Object toAdd(User user){
        AjaxResult result = new AjaxResult();
        try {
            int count = userService.saveUser(user);
            result.setSuccess(count==1);
        }catch (Exception e){
            result.setSuccess(false);
            e.printStackTrace();
            result.setMessage("保存数据失败！");
        }
        return result;
    }
    //转到修改页面
    @RequestMapping("/toUpdate")
    public String toUpdate(Integer id,Map map){
        User user = userService.getUserById(id);
        map.put("user",user);
        return "user/update";
    }
    //修改数据
    @ResponseBody
    @RequestMapping("/doUpdate")
    public Object doUpdate(User user){
        AjaxResult result = new AjaxResult();
        try {
            int count = userService.updateUser(user);
            result.setSuccess(count==1);
        }catch (Exception e){
            result.setSuccess(false);
            e.printStackTrace();
            result.setMessage("修改数据失败!!!");
        }
        return result;
    }
    //删除数据
    @ResponseBody
    @RequestMapping("/doDelete")
    public Object doDelete(Integer id){
        AjaxResult result = new AjaxResult();
        try {
            int count = userService.deleteUser(id);
            result.setSuccess(count==1);
        }catch (Exception e){
            result.setSuccess(false);
            e.printStackTrace();
            result.setMessage("删除用户失败！！！");
        }
        return result;
    }
    //批量删除
    @ResponseBody
    @RequestMapping("/doDeleteBatch")
    public Object doDeleteBatch(Data data){
        AjaxResult result = new AjaxResult();
        try {
            int count = userService.deleteBatchUserByVO(data);
            result.setSuccess(count==data.getUserList().size());
        }catch (Exception e){
            result.setSuccess(false);
            e.printStackTrace();
            result.setMessage("删除用户失败!!!");
        }
        return result;
    }
}
