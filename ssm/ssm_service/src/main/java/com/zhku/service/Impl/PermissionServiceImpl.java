package com.zhku.service.Impl;

import com.github.pagehelper.PageHelper;
import com.zhku.dao.PermissionDao;
import com.zhku.pojo.Permission;
import com.zhku.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public List<Permission> findAll(int page, int size) {

//        使用PageHelper分页
        PageHelper.startPage(page, size);
        return permissionDao.findAll();
    }

    @Override
    public void save(Permission permission) {
        permissionDao.save(permission);

    }
}
