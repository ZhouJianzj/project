package com.zj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author HuXinJie
 * @version 1.0
 * @date 2021/11/4 14:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item implements Serializable {

    private int id;

    private String name;

    private String number;

    private int orgaId;

    private Organize organize;

    private String addr;

    private String phone;

    private Date createTime;
}
