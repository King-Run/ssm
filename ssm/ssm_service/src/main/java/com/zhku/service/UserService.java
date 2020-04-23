package com.zhku.service;

import com.zhku.pojo.Role;
import com.zhku.pojo.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {


    public List<UserInfo> findAll(int page, int size);

    public void save(UserInfo userInfo);

    public UserInfo findById(int id);

    public List<Role> findOtherRoles(int id);

    public void addRoleToUser(int userId, String[] roleIds);
}
