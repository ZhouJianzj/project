package com.zj.service;

import com.zj.entity.Item;

import java.util.List;

/**
 * @author HuXinJie
 * @version 1.0
 * @date 2021/11/4 13:59
 */
public interface ItemService {
    List<Item> selectItemSelect(String key);
}
