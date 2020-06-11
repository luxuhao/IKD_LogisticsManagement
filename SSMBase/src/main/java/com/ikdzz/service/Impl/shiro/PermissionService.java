package com.ikdzz.service.Impl.shiro;

import com.ikdzz.domain.shiro.Permission;
import com.ikdzz.domain.shiro.Role;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PermissionService {

    // 查询所有权限
    public List<Permission> findAllPermissions();

    //查询权限
    public Permission findPermissionById(String permission_id);

    //查询子菜单
    public List<Permission> findMenuChirdlrensById(String permission_id);



}
