package com.zj.monitorManager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

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

    private OrgaType type;

    private String location;

    private String orgaNumber;

    private String ext;

    private List<Organize> children;
}