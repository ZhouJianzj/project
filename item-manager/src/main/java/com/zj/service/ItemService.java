package com.zj.service;

import com.zj.entity.CommonResponse;
import com.zj.entity.Item;

import java.util.List;

/**
 * @author HuXinJie
 * @version 1.0
 * @date 2021/11/4 13:59
 */
public interface ItemService {

    List<Item> selectItemSelect(String key);

    CommonResponse<Boolean> deleteItemService(int id);

    CommonResponse<Item> insertItemService(Item item);

    CommonResponse<Item> modifyItemService(Item item);
}
