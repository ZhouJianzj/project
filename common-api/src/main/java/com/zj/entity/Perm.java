package com.zj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (TPerms)实体类
 *
 * @author zhoujian
 * @since 2021-11-02 22:30:59
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Perm implements Serializable {

    private Integer id;

    private String name;

}