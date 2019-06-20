package com.qf.j1902.shiro;

import com.qf.j1902.pojo.Member;
import com.qf.j1902.pojo.Permission;
import com.qf.j1902.pojo.User;
import com.qf.j1902.service.MemberService;
import com.qf.j1902.service.UserService;
import com.qf.j1902.utils.Const;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;


/**
 * 自定义安全策略
 */
public class MyShiroRealm extends AuthorizingRealm {
//    注入userService
    @Autowired
    private UserService userService;
    @Autowired
    private MemberService memberService;
//    授权系统
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//       获得主体对象
        Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession();
            User user = (User)session.getAttribute(Const.LOGIN_USER);
            List<Permission> permissions = userService.queryPermissionByUserid(user.getId());
//        权限去重
            Collection<String> perms = new HashSet<>();
            for (Permission perm : permissions){
                perms.add(perm.getName());
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(perms);
        return info;
    }
//    用户认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//       获取token中的用户信息
        String loginacct = (String)authenticationToken.getPrincipal();
//        根据用户名查询数据库获得后端用户的身份信息，转交给securityManager判定
        User user = userService.selectUserByLoginacct(loginacct);
        Member member = memberService.selectMemberByLoginacct(loginacct);
        SimpleAuthenticationInfo authenticationInfo = null;
        if (user != null){
            authenticationInfo = new SimpleAuthenticationInfo(loginacct,user.getUserpswd(),this.getName());
        }
        if (member != null){
            authenticationInfo = new SimpleAuthenticationInfo(loginacct,member.getUserpswd(),this.getName());
        }
        return authenticationInfo;
    }
}
