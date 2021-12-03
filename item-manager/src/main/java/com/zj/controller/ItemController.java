package com.zj.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zj.entity.CommonResponse;
import com.zj.entity.Item;
import com.zj.entity.ItemCount;
import com.zj.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * @param key  查询的关键字
     * @param pageNo 页面
     * @param pageSize 一页数据量
     * @return 返回的自定义分页对象
     * */
    @GetMapping("item")
    public PageInfo<Item> findItemController(@RequestParam(value = "pageNo",defaultValue = "1") String pageNo,
                                             @RequestParam(value = "pageSize",defaultValue = "8") String pageSize,
                                             @RequestParam(value = "key") String key){

        PageHelper.startPage(Integer.parseInt(pageNo),Integer.parseInt(pageSize));
        return new PageInfo<Item>(itemService.selectItemSelect(key));
    }

    /**
     * 根据orga_id查询项目
     * @param orgaId 组织id
     * @return 返回项目列表对象
     * */
    @GetMapping("item/orgaId")
    public List<Item> selectItemByOrgaIdController(@RequestParam(value = "orgaId") String orgaId){
        return itemService.selectItemByOrgaIdService(orgaId);
    }
    /**
     * 删除项目
     * @param id 项目id
     * @return boolean 删除是否成功
     * */
    @DeleteMapping("item")
    public CommonResponse<Boolean> deleteItemController(@RequestParam(value = "id") int id){
        return itemService.deleteItemService(id);
    }

    /**
     * 添加项目
     * @param item 项目数据
     * @return
     * */
    @PostMapping("item")
    public CommonResponse<Boolean> addItemController(@RequestBody Item item){
        return itemService.insertItemService(item);
    }

    /**
     * 修改item
     * @param item
     * @return
     */
    @PutMapping("item")
    public CommonResponse<Item> modifyItemController(@RequestBody Item item){
        return itemService.modifyItemService(item);
    }

    /**
     * 查询组织下的项目数
     * @return
     * */
    @GetMapping("item/count")
    public List<ItemCount> selectItemCountController(){
        return itemService.selectItemCountService();
    }

}
