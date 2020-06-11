package com.ikdzz.dao.shirodao;

import com.ikdzz.domain.shiro.Role;
import com.ikdzz.domain.shiro.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRoledao {

    @Select("select * from ikd_shiro_role")
    public List<Role> findAllRoles();

    @Select("sString role_idelect * from ikd_shiro_role where role_id = (#{role_id})")
    public Role findRoleByRoleId(String role_id);


}
