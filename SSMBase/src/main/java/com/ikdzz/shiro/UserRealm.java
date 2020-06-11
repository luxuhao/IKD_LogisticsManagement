package com.ikdzz.shiro;

import com.ikdzz.domain.shiro.Role;
import com.ikdzz.domain.shiro.User;
import com.ikdzz.service.Impl.shiro.RoleService;
import com.ikdzz.service.Impl.shiro.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    //为当前登录成功的用户授予权限和角色，已经登录成功了。
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal(); //获取到的是 username
        Set<String> roles = new TreeSet<String>();
        roles.add(userService.findRoleByUserName(username));
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roles);
        //需要的是set集合类
        authorizationInfo.setStringPermissions(new HashSet<String>(userService.findPermissionsByUserName(username)));

        return authorizationInfo;
    }

    //验证当前登录的用户，获取认证信息。
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String user_id = (String) token.getPrincipal();//获取用户名
        String password = new String((char[]) token.getCredentials());//获取用户密码

        User user = userService.findUserForLogin(user_id);

        if (user == null) {
            throw new UnknownAccountException("此用户不存在");
        }
        if (user.getPassword().equals(password)==false) {
            throw new IncorrectCredentialsException("密码不正确");
        }
        AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), "myRealm");
        System.out.print("认证成功！");
        return authcInfo;
    }





}