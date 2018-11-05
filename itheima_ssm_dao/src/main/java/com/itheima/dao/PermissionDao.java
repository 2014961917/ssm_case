package com.itheima.dao;

import com.itheima.domain.Permission;

import java.util.List;

public interface PermissionDao {
    //根据角色id查询旗下的权限
    List<Permission> findPermissionByRoleId(String roleId) throws Exception;

    List<Permission> findAll();

    void save(Permission permission);

}
