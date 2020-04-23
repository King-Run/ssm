package com.zhku.dao;

import com.zhku.pojo.Permission;
import com.zhku.pojo.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao {

    @Select("select * from role where id in(select roleId from users_role where userId = #{id})")
    @Results(    {
            @Result(id=true,column="id",property="id"),
            @Result(column="roleName",property="roleName"),
            @Result(column="roleDesc",property="roleDesc"),
            @Result(column="id",property="permissions",javaType=List.class,many=@Many(select="com.zhku.dao.PermissionDao.findByRoleId"))    })
    public List<Role> findRoleByUserId(int id);

    @Select("select * from role")
    public List<Role> findAll();

    @Insert("insert into role(roleName,roleDesc) values (#{roleName},#{roleDesc})")
    public void save(Role role);

    @Select("select * from role where id = #{id}")
    public Role findById(int id);

    @Select("select * from permission where id not in (select permissionId from role_permission where roleId = #{id})")
    public List<Permission> findOtherPermission(int id);

    @Insert("insert into  role_permission(roleId,permissionId) values(#{id},#{permissionId})")
    public void addPermissionToRole(@Param("id") int id, @Param("permissionId") String permissionId);
}
