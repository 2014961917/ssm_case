package com.itheima.service;

import com.itheima.domain.Permission;

import java.util.List;

public interface PermissionService {
    List<Permission> findAll(int page,int size);

    void save(Permission permission);
}
