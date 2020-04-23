package com.zhku.service.Impl;

import com.github.pagehelper.PageHelper;
import com.zhku.dao.RoleDao;
import com.zhku.pojo.Permission;
import com.zhku.pojo.Role;
import com.zhku.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> findAll(int page, int size) {

//        PageHelper分页查询
        PageHelper.startPage(page, size);
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public Role findById(int id) {
        return roleDao.findById(id);
    }

    @Override
    public List<Permission> findOtherPermission(int id) {
        return roleDao.findOtherPermission(id);
    }

    @Override
    public void addPermissionToRole(int id, String[] permissionIds) {
        for (String permissionId:permissionIds
             ) {
            roleDao.addPermissionToRole(id,permissionId);
        }


    }
}
