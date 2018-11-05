package com.itheima.dao;

import com.itheima.domain.Role;

import java.util.List;

public interface RoleDao {
    //根据用户id查询旗下的角色和权限
    List<Role> findRoleByUserId(String userId) throws Exception;

    //查找所有
    List<Role> findAll() throws Exception;


    void save(Role role);

    Role findById(String id);

    void delRole(String id);

    void update(Role role);
}
