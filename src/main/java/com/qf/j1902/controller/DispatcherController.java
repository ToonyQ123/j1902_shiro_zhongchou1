package com.qf.j1902.controller;


import com.qf.j1902.pojo.Member;
import com.qf.j1902.pojo.User;
import com.qf.j1902.service.MemberService;
import com.qf.j1902.service.UserService;
import com.qf.j1902.utils.AjaxResult;
import com.qf.j1902.utils.Const;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class DispatcherController {
    @Autowired
    private UserService userService;
    @Autowired
    private MemberService memberService;
    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }
    @RequestMapping(value = "/reg",method = RequestMethod.GET)
    public String reg(){
        return "reg";
    }
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }
//    @RequiresPermissions(value = {"控制面板"})
    @RequestMapping("/main")
    public String main(HttpSession session){
        return "main";
    }
    @RequestMapping("/member")
    public String member(HttpSession session){
        return "/member/member";
    }
    @ResponseBody
    @RequestMapping(value = "/doLogin")
    public Object doLogin(String loginacct, String userpswd, String usertype, Map map){
        AjaxResult result = new AjaxResult();
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("loginacct",loginacct);
        paramMap.put("userpswd",userpswd);
        paramMap.put("usertype",usertype);
        if ("member".equals(usertype)){
            Member member = memberService.queryMebmerlogin(paramMap);
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession();
            session.setAttribute(Const.LOGIN_MEMBER,member);
            UsernamePasswordToken token = new UsernamePasswordToken(loginacct, userpswd);
            subject.login(token);
            if (subject.isAuthenticated()){
                result.setData(usertype);
                result.setSuccess(true);
            }
        }else if ("user".equals(usertype)){
            User user = userService.queryUserlogin(paramMap);
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession();
            session.setAttribute(Const.LOGIN_USER,user);
            UsernamePasswordToken token = new UsernamePasswordToken(loginacct, userpswd);
            subject.login(token);
            if (subject.isAuthenticated()){
                result.setData(usertype);
                result.setSuccess(true);
            }
        }else {
            result.setMessage("登录失败");
            result.setSuccess(false);
        }
        return result;
    }
    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:login";
    }
}
