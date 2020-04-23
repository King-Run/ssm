package com.zhku.service;

import com.zhku.pojo.Permission;

import java.util.List;

public interface PermissionService {

    public List<Permission> findAll(int page, int size);

    public void save(Permission permission);
}
