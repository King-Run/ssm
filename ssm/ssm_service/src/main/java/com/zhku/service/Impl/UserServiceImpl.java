package com.zhku.service.Impl;

import com.github.pagehelper.PageHelper;
import com.zhku.dao.UserDao;
import com.zhku.pojo.Role;
import com.zhku.pojo.UserInfo;
import com.zhku.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    //因为在spring-security。xml里面已经把BCryptPasswordEncoder对象交给Spring管理了
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;



    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserInfo userInfo = null;

        try {
            userInfo = userDao.findByUsername(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        把自己的对象封装成security使用的对象

//        因为用这些参数我们还不能指定账户是否可用，所以我们要一个更加强大的方法重载
//        User user = new User(userInfo.getUsername(), "{noop}" + userInfo.getPassword(), getAuthority(userInfo.getRoles()));
//          在spring-security中已经配置了密码加密。而且实际也使用了密码加密，所以不用加{noop}前缀
        User user = new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatus() == 0 ? false : true, true, true, true,getAuthority(userInfo.getRoles()));
        return user;
    }

    private List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {
        List<SimpleGrantedAuthority> authoritys = new ArrayList();
        for (Role role : roles) {
            authoritys.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return authoritys;
    }

    /**
     * 查询所有的用户
     * @return
     * @param page
     * @param size
     */
    @Override
    public List<UserInfo> findAll(int page, int size) {

//        page是当前页面，size是每页条数
        PageHelper.startPage(page, size);
        return userDao.findAll();
    }

    @Override
    public void save(UserInfo userInfo) {
//        这时候给密码加密
        String encode = bCryptPasswordEncoder.encode(userInfo.getPassword());
        userInfo.setPassword(encode);
        userDao.save(userInfo);
    }

    @Override
    public UserInfo findById(int id) {
        return userDao.findById(id);
    }

    @Override
    public List<Role> findOtherRoles(int id) {
        return userDao.findOtherRoles(id);
    }

    @Override
    public void addRoleToUser(int userId, String[] roleIds) {
        for (String roleId :roleIds
             ) {
            userDao.addRoleToUser(userId,roleId);
        }

    }
}
