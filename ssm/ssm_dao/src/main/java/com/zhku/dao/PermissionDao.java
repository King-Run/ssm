package com.zhku.dao;

import com.zhku.pojo.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionDao {

    @Select("select * from permission where id in (select permissionId from role_permission where roleId = #{roleId})")
    public List<Permission> findByRoleId(int roleId);

    @Select("select * from permission")
    public List<Permission> findAll();

    @Insert("insert into permission(permissionName,url) values (#{permissionName},#{url})")
    public void save(Permission permission);
}
