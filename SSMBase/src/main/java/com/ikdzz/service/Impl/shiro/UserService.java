package com.ikdzz.service.Impl.shiro;

import com.ikdzz.domain.shiro.Permission;
import com.ikdzz.domain.shiro.Role;
import com.ikdzz.domain.shiro.User;

import java.util.List;

public interface UserService {
    // 查询所有账户
    public List<User> findAll();

    // 保存帐户信息
    public void saveUser(User user);

    //查询用户
    public User findUserForLogin(String user_id);

    //查询用户的角色
    public String findRoleByUserId(String user_id);
    //查询用户的角色
    public String findRoleByUserName(String username);


    //查询用户的角色下的权限
    public List<Permission> findPermissionsByUserId(String user_id);
    //查询用户的角色下的权限
    public List<String> findPermissionsByUserName(String username);


}