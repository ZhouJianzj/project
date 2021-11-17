package com.zj.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author HuXinJie
 * @version 1.0
 * @date 2021/11/6 21:07
 */
@Data
public class RolePerm implements Serializable {
    int id;
    int roleId;
    int permId;
    Integer[] permIdArrays;
}
