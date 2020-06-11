package com.ikdzz.service.Impl.shiro;

import com.ikdzz.dao.shirodao.IRoledao;
import com.ikdzz.domain.shiro.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("RoleService")
public class RoleServiceImpl implements RoleService{

    @Autowired
    private IRoledao iRoledao;

    @Override
    public List<Role> findAllRoles() {
        System.out.println("Service业务层：查询所有角色...");
        return iRoledao.findAllRoles();
    }

    @Override
    public Role findRoleByRoleId(String role_id) {
        System.out.println("Service业务层：查询某个角色...");
        return iRoledao.findRoleByRoleId(role_id);
    }
}
