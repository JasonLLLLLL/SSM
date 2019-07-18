package com.jason.dao;

import com.jason.bean.Role;

import java.util.List;

public interface IRoleDao {
    List<Role> findRoleByUserId(int id);
}
