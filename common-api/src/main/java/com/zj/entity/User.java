package com.zj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (TUser)实体类
 *
 * @author zhoujian
 * @since 2021-11-02 22:08:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {


    private Integer id;

    private String username;

    private String password;

    private String salt;

    private String phone;

    private Integer orgaId;

    private Integer roleId;

    private Integer[] roleIdArrays;


}