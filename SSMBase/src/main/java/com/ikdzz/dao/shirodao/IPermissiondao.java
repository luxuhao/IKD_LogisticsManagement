package com.ikdzz.dao.shirodao;

import com.ikdzz.domain.shiro.Permission;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPermissiondao {


    @Select("select * from ikd_shiro_permission")
    public List<Permission> findAllPermissions();

    @Select("select * from ikd_shiro_permission where permission_id = (#{permission_id})")
    public Permission findPermissionById(String permission_id);

    //查询菜单下所含的子菜单
    @Select("select * from ikd_shiro_permission where parent_id = (#{permission_id})")
    public List<Permission> findMenuChirdlrensById(String permission_id);



}
