package com.qf.j1902.controller;

import com.qf.j1902.pojo.Member;
import com.qf.j1902.service.MemberService;
import com.qf.j1902.utils.AjaxResult;
import com.qf.j1902.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.dc.pr.PRError;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;
    @RequestMapping("/accttype")
    public String acctType(){
        return "/member/accttype";
    }
    @RequestMapping("/basicinfo")
    public String basicInfo(){
        return "/member/basicinfo";
    }
    @RequestMapping("/uploadCert")
    public String uoLoadCert(){
        return "/member/uploadcert";
    }
    @ResponseBody
    @RequestMapping("/updateAcctType")
    public Object updateAcctType(HttpSession session,String accttype){
        AjaxResult result = new AjaxResult();
        try {
            Member member = (Member)session.getAttribute(Const.LOGIN_MEMBER);
            member.setAccttype(accttype);
            memberService.updateAcctType(member);
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
        }
        return result;
    }
    @ResponseBody
    @RequestMapping("/updateBasicinfo")
    public Object updateBasicInfo(HttpSession session,Member member){
        AjaxResult result = new AjaxResult();
        try {
            Member loginMember = (Member)session.getAttribute(Const.LOGIN_MEMBER);
            loginMember.setRealname(member.getRealname());
            loginMember.setCardnum(member.getCardnum());
            loginMember.setTel(member.getTel());
            memberService.updateBasicinfo(loginMember);
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
        }
        return result;
    }
}
