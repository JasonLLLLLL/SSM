package com.jason.dao;


import com.jason.bean.UserInfo;

import java.util.List;

public interface IUserInfoDao {
    List<UserInfo> findAll();
    UserInfo login(UserInfo userInfo);
    void delete(int id);
    void add(UserInfo userInfo);
    UserInfo selectById(int id);
    void update(UserInfo userInfo);
    UserInfo findByUserName(String username);
    void deleteAll(List<Integer> ids);

}
