package com.zj.service;

import com.zj.dao.ItemDao;
import com.zj.entity.CommonResponse;
import com.zj.entity.Item;
import com.zj.entity.ItemCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author HuXinJie
 * @version 1.0
 * @date 2021/11/4 14:00
 */
@Service

public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemDao itemDao;

    /**
     * 查询项目
     * @param key
     * */
    @Override
    public List<Item> selectItemSelect(String key) {
        return itemDao.itemSelect(key);
    }

    /**
     * 删除项目
     * @param id
     * */
    @Override
    public CommonResponse<Boolean> deleteItemService(int id) {
        CommonResponse<Boolean> response = new CommonResponse<>();
        if(itemDao.itemDelete(id)){
            itemDao.itemPipeDelete(id);
            itemDao.itemSensorDelete(id);
            response.setMsg("删除成功");
            response.setStatus(200);
        }else {
            response.setMsg("删除失败");
            response.setStatus(400);
        }
        return response;
    }

    /**
     * 新增项目
     * @param item
     * */
    @Override
    public CommonResponse<Boolean> insertItemService(Item item) {
        CommonResponse<Boolean> response = new CommonResponse<>();
        if (itemDao.itemInsert(item)){
            response.setMsg("新增成功");
            response.setStatus(200);
        }else {
            response.setMsg("新增失败");
            response.setStatus(400);
        }
        return response;
    }

    @Override
    public CommonResponse<Item> modifyItemService(Item item) {
        CommonResponse<Item> response = new CommonResponse<>();
        if (itemDao.itemModify(item)){
            response.setMsg("修改成功");
            response.setStatus(200);
        }else {
            response.setMsg("修改失败");
            response.setStatus(400);
        }
        return response;
    }

    /**
     * 根据orga_id查询项目
     * @param orgaId
     * */
    @Override
    public List<Item> selectItemByOrgaIdService(String orgaId) {
        return itemDao.itemByOrgaIdSelect(orgaId);
    }

    /**
     * 查询组织下的项目数量
     * */
    @Override
    public List<ItemCount> selectItemCountService() {
        return itemDao.itemCountSelect();
    }
}
