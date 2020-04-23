package com.zhku.service;

import com.zhku.pojo.Permission;
import com.zhku.pojo.Role;

import java.util.List;

public interface RoleService {

    public List<Role> findAll(int page, int size);

    public void save(Role role);

    public Role findById(int id);

    public List<Permission> findOtherPermission(int id);

    public void addPermissionToRole(int id, String[] permissionIds);
}
