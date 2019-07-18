package com.jason.service;


import com.jason.bean.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserInfoService extends UserDetailsService {
    List<UserInfo> findAll(int page,int size);
    boolean login(UserInfo userInfo);
    void delete(int id);
    void add(UserInfo userInfo);
    UserInfo selectById(int id);
    void update(UserInfo userInfo);
    void deleteAll(List<Integer> ids);

}
