package com.ikdzz.dao.shirodao;

import com.ikdzz.domain.shiro.Permission;
import com.ikdzz.domain.shiro.Role;
import com.ikdzz.domain.shiro.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository  //此注解代表这是一个持久层，用法类似@controller、@service 饿呢
public interface IUserdao {

    @Select("select * from ikd_shiro_user")
    public List<User> findAll();

    //@Insert("insert into ikd_shiro_user (name,money) values (#{name},#{money})")
   // public void saveAccount(User user);

    @Select("select * from ikd_shiro_user where user_id = (#{user_id})")
    public User findUserForLogin(String user_id);

    //查询登陆用户角色(单个)
    @Select("SELECT a.role_id FROM dbo.ikd_shiro_user_role  a\n" +
            "INNER  JOIN dbo.ikd_shiro_role b ON a.role_id= b.role_id\n" +
            "WHERE a.USER_ID  =(#{user_id})")
    public String findRoleByUserId(String user_id);

    @Select("SELECT a.role_id FROM dbo.ikd_shiro_user_role  a\n" +
            "INNER JOIN dbo.ikd_shiro_role b ON a.role_id= b.role_id\n" +
            "WHERE a.USER_ID = (SELECT USER_ID FROM dbo.ikd_shiro_user WHERE username=(#{username}))")
    public String findRoleByUserName(String username);



    //查询登陆用户所拥有的角色下的权限
    @Select("SELECT * FROM ikd_shiro_user_role a \n" +
            "INNER JOIN dbo.ikd_shiro_role_permission b ON a.role_id = b.role_id\n" +
            "LEFT JOIN dbo.ikd_shiro_permission c ON c.permission_id = b.permission_id\n"+
            "WHERE a.user_id =(#{user_id})")
    public List<Permission> findPermissionsByUserId(String user_id);

    @Select("SELECT b.permission_id FROM ikd_shiro_user_role a \n" +
            "INNER JOIN dbo.ikd_shiro_role_permission b ON a.role_id = b.role_id\n" +
            "LEFT JOIN dbo.ikd_shiro_permission c ON c.permission_id = b.permission_id\n"+
            "WHERE a.user_id = (SELECT USER_ID FROM dbo.ikd_shiro_user WHERE username=(#{username})) and c.type=0")
    public List<String> findPermissionsByUserName(String username);








}
