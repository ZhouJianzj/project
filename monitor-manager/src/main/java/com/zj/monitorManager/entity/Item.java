package com.zj.monitorManager.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    private Organize organize;

    private String addr;

    private String phone;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}