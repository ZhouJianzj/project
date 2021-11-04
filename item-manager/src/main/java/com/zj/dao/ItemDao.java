package com.zj.dao;

import com.zj.entity.Item;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author HuXinJie
 * @version 1.0
 * @date 2021/11/4 14:00
 */
@Repository
public interface ItemDao {
    List<Item> itemSelect(String key);
}
