package com.zj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Organize)实体类
 *
 * @author zhoujian
 * @since 2021-11-02 22:04:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Organize implements Serializable {

    private Integer id;
    
    private String name;
    
    private Integer parentId;
    
    private Integer typeId;
    
    private String location;
    
    private String orgaNumber;
    
    private String ext;


}