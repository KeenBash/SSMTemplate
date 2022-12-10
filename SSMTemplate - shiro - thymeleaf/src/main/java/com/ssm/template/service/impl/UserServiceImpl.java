package com.ssm.template.service.impl;

import com.ssm.template.entity.User;
import com.ssm.template.mapper.UserMapper;
import com.ssm.template.service.UserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserMapper mapper;

    @Override
    public User getUserByUsername(String username) {
        return mapper.getUserByUsername(username);
    }

    @Override
    public void registerUser(String username, String password, String salt) {
        // 加密处理
        Md5Hash md5Hash = new Md5Hash(password, salt, 3);
        mapper.registerUser(username, md5Hash.toHex(), salt);
    }

    @Override
    public List<User> getAllUsers() {
        return mapper.getAllUsers();
    }

    @Override
    public String getRoleByUid(int uid) {
        return mapper.getRoleByUid(uid);
    }

    @Autowired
    public void setMapper(UserMapper mapper) {
        this.mapper = mapper;
    }
}
