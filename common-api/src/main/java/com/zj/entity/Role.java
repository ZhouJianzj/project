package com.zj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * (TRole)实体类
 *
 * @author zhoujian
 * @since 2021-11-02 22:06:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {

    private Integer id;

    private String name;

    private String ext;

    private List<Perm> perms;


}