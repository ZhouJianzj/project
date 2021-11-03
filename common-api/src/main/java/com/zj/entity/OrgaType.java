package com.zj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (OrgaType)实体类
 *
 * @author zhoujian
 * @since 2021-11-02 23:44:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrgaType implements Serializable {

    private Integer id;
    
    private String name;


}