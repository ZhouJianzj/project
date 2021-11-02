package com.zj.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserManager implements Serializable {
    Integer id;
    String username;
    String password;
    String salt;
    String phone;
    Organize o;
    List<Role> roles;
}