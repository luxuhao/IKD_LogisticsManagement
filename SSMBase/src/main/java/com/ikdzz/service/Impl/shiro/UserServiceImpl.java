package com.ikdzz.service.Impl.shiro;

import com.ikdzz.dao.shirodao.IUserdao;
import com.ikdzz.domain.shiro.Permission;
import com.ikdzz.domain.shiro.Role;
import com.ikdzz.domain.shiro.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private IUserdao iUserdao;

    @Override
    public List<User> findAll() {
        System.out.println("Service业务层：查询所有账户...");
        return iUserdao.findAll();
    }

    @Override
    public void saveUser(User user) {

    }


    // @Override
    //public void saveAccount(User user) {
    //    System.out.println("Service业务层：保存帐户...");
   //     iUserdao.saveAccount(user);  //调用service中的saveAccount(account)方法
    //}

    @Override
    public User findUserForLogin(String user_id) {
        System.out.println("Service业务层：查询当前登陆用户权限...");
        return iUserdao.findUserForLogin(user_id);
    }

    @Override
    public String findRoleByUserId(String user_id) {
        System.out.println("Service业务层：查询当前登陆用户角色...");
        return iUserdao.findRoleByUserId(user_id);
    }

    @Override
    public String findRoleByUserName(String username) {
        System.out.println("Service业务层：查询当前登陆用户角色...");
        return iUserdao.findRoleByUserName(username);
    }

    @Override
    public List<Permission> findPermissionsByUserId(String user_id) {
        System.out.println("Service业务层：查询当前登陆用户角色权限...");

        for (Permission permission : iUserdao.findPermissionsByUserId(user_id)) {
            System.out.println("角色权限"+permission);
        }


        return iUserdao.findPermissionsByUserId(user_id);
    }

    @Override
    public List<String> findPermissionsByUserName(String username) {
        System.out.println("Service业务层：查询当前登陆用户角色权限...");

        for (String permission : iUserdao.findPermissionsByUserName(username)) {
            System.out.println("角色权限"+permission);
        }

        return iUserdao.findPermissionsByUserName(username);
    }


}

