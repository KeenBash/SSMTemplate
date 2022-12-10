package com.ssm.template.mapper;

import com.ssm.template.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    User getUserByUsername(String username);

    void registerUser(@Param("username")String username, @Param("password")String password, @Param("salt")String salt);

    List<User> getAllUsers();

    String getRoleByUid(int uid);
}
