package com.itheima.service;

import com.itheima.domain.Role;

import java.util.List;

public interface RoleService {
    //查询所有
    List<Role> findAll(int page, int size) throws Exception;

    void save(Role role);

    Role findById(String id);

    void delRole(String id);

    void update(Role role);
}
