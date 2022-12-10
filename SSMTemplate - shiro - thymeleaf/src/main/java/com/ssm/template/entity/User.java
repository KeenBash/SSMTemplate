package com.ssm.template.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    int uid;
    String username;
    String password;
    String salt;
}
