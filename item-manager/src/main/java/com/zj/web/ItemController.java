package com.zj.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zj.entity.Item;
import com.zj.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("item")
    public PageInfo<Item> selectItemController(String pageNo, String pageSize, String key){

        PageHelper.startPage(Integer.valueOf(pageNo),Integer.valueOf(pageSize));
        return new PageInfo<Item>(itemService.selectItemSelect(key));
    }
}
