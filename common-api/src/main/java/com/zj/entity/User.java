package com.zj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private String id;
    private String username;
    /**
     * 真实姓名realname
     */
    private String realName;
    private String password;
    private String salt;
    private String avatar;
    private Date birthday;
    private Boolean sex;
    private String email;
    private String phone;
    /**
     * 组织代码org_code
     */
    private String orgCode;
    /**
     *工号work_no
     */
    private String workNo;
    private String post;

    private String token;


}
