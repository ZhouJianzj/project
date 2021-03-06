package com.zj.dao;

import com.zj.entity.Item;
import com.zj.entity.ItemCount;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author HuXinJie
 * @version 1.0
 * @date 2021/11/4 14:00
 */
@Repository
public interface ItemDao {
    /**
     * 查询项目
     * */
    List<Item> itemSelect(String key);

    /**
     * 删除项目
     * */
    boolean itemDelete(int id);

    /**
     * 新增项目
     * */
    boolean itemInsert(Item item);

    /**
     * 修改项目
     * */
    boolean itemModify(Item item);

    boolean itemPipeDelete(int id);

    void itemSensorDelete(int id);

    /**
     * 根据orga_id查询项目
     * */
    List<Item> itemByOrgaIdSelect(String orgaId);

    List<ItemCount> itemCountSelect();
}
