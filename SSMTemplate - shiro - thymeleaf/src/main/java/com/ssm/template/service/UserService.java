package com.ssm.template.service;

import com.ssm.template.entity.User;

import java.util.List;

public interface UserService {

    User getUserByUsername(String username);

    void registerUser(String username, String password, String salt);

    List<User> getAllUsers();

    String getRoleByUid(int uid);
}
