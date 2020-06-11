package com.ikdzz.service.Impl.shiro;

import com.ikdzz.dao.shirodao.IPermissiondao;
import com.ikdzz.domain.shiro.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("PermissionService")
public class PermissionServiceImpl implements PermissionService{

    @Autowired
    private  IPermissiondao iPermissiondao;

    @Override
    public List<Permission> findAllPermissions() {
        System.out.println("Service业务层：查询所有权限...");
        return iPermissiondao.findAllPermissions();
    }

    @Override
    public Permission findPermissionById(String permission_id) {
        System.out.println("Service业务层：查询某个权限...");
        return iPermissiondao.findPermissionById(permission_id);
    }

    @Override
    public List<Permission> findMenuChirdlrensById(String permission_id) {
        System.out.println("Service业务层：查询子菜单...");
        return iPermissiondao.findMenuChirdlrensById(permission_id);

    }

}
