package com.zj.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zj.entity.CommonResponse;
import com.zj.entity.Item;
import com.zj.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author HuXinJie
 * @version 1.0
 * @date 2021/11/4 13:57
 */
@RestController
@RequestMapping("item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    /**
     * 查询项目
     * @param key
     * @param pageNo
     * @param pageSize
     * @return item
     * */
    @GetMapping("item")
    public PageInfo<Item> selectItemController(String pageNo, String pageSize, String key){

        PageHelper.startPage(Integer.valueOf(pageNo),Integer.valueOf(pageSize));
        return new PageInfo<Item>(itemService.selectItemSelect(key));
    }

    /**
     * 删除项目
     * @param id
     * @return boolean
     * */
    @DeleteMapping("item")
    public CommonResponse<Boolean> deleteItemController(int id){
        return itemService.deleteItemService(id);
    }

    /**
     * 添加项目
     * @param item
     * @return item
     * */
    @PostMapping("item")
    public CommonResponse<Item> insertItemController(@RequestBody Item item){
        return itemService.insertItemService(item);
    }

    @PutMapping("item")
    public CommonResponse<Item> modifyItemController(@RequestBody Item item){
        return itemService.modifyItemService(item);
    }
}
