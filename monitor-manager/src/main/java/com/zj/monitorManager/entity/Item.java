package com.zj.monitorManager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

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

    private List<Pipe> pipes;
}
