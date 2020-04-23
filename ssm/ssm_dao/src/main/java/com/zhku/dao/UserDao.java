package com.zhku.dao;

import com.zhku.pojo.Role;
import com.zhku.pojo.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    @Select("select * from users where username =#{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "email", property = "email"),
            @Result(column = "password", property = "password"),
            @Result(column = "phoneNum", property = "phoneNum"),
            @Result(column = "status", property = "status"),
            @Result(column = "id", property = "roles", javaType = List.class, many = @Many(select = "com.zhku.dao.RoleDao.findRoleByUserId"))})
    public UserInfo findByUsername(String username) throws Exception;

    @Select("select * from users")
    public List<UserInfo> findAll();

    @Insert("insert into users(email,username,password,phoneNum,status) value(#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo);

    @Select("select * from users where id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "email", property = "email"),
            @Result(column = "password", property = "password"),
            @Result(column = "phoneNum", property = "phoneNum"),
            @Result(column = "status", property = "status"),
            @Result(column = "id", property = "roles", javaType = List.class, many = @Many(select = "com.zhku.dao.RoleDao.findRoleByUserId"))})
    UserInfo findById(int id);

    @Select("select * from role where id not in(select roleId from users_role where userId = #{id})")
    List<Role> findOtherRoles(int id);

    @Insert("insert into users_role(userId,roleId) values (#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") int userId,@Param("roleId") String roleId);
}
