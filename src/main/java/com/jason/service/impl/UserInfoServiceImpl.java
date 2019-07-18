package com.jason.service.impl;


import com.github.pagehelper.PageHelper;
import com.jason.bean.Role;
import com.jason.bean.UserInfo;
import com.jason.dao.IRoleDao;
import com.jason.dao.IUserInfoDao;
import com.jason.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("userInfoService")
public class UserInfoServiceImpl implements IUserInfoService {

    @Autowired
    private IUserInfoDao userInfoDao;
    @Autowired
    private IRoleDao roleDao;
    @Override
    public List<UserInfo> findAll(int page,int size) {
        PageHelper.startPage(page,size);
        return userInfoDao.findAll();
    }

    @Override
    public boolean login(UserInfo userInfo) {
        UserInfo user = userInfoDao.login(userInfo);
        if(user!=null) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void delete(int id) {
        userInfoDao.delete(id);
    }

    @Override
    public void add(UserInfo userInfo) {
        userInfoDao.add(userInfo);
    }

    @Override
    public UserInfo selectById(int id) {
        return userInfoDao.selectById(id);
    }

    @Override
    public void update(UserInfo userInfo) {
        userInfoDao.update(userInfo);
    }

    @Override
    public void deleteAll(List<Integer> ids) {
        userInfoDao.deleteAll(ids);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userinfo = userInfoDao.findByUserName(username);
        List<Role> roles = roleDao.findRoleByUserId(userinfo.getId());
        User user=new User(userinfo.getUsername(),"{noop}"+userinfo.getPassword(),getAuthority(roles));
        return user;
    }

    private Collection<? extends GrantedAuthority> getAuthority(List<Role> roles) {
        List<SimpleGrantedAuthority> list=new ArrayList<>();
        for (Role role:roles){
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRolename()));
        }
        return list;
    }
}
