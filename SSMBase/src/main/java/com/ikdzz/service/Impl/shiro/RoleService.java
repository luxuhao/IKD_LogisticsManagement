package com.ikdzz.service.Impl.shiro;

import com.ikdzz.domain.shiro.Role;

import java.util.List;

public interface RoleService {

    // 查询所有角色
    public List<Role> findAllRoles();


    //查询角色
    public Role findRoleByRoleId(String role_id);


}
