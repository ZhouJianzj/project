package com.zj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author HuXinJie
 * @version 1.0
 * @date 2021/12/3 13:19
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemCount implements Serializable {
    private int id;

    private Organize organize;

    private int count;
}
