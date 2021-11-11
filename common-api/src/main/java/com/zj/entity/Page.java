package com.zj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


/**
 * 自定的分页插件工具类
 * @param <T>
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Page<T> implements Serializable {
    private List <T> list;
    private int total;
    private int pageNo;
    private int pageSize;
    private int size;

    public Page(List<T> list, int pageNo, int pageSize){
        this.list = list;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }
}
