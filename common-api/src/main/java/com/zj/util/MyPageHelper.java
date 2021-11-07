package com.zj.util;

import com.zj.entity.Page;

import java.util.List;

/**
 * @author zhoujian
 */
public class MyPageHelper {

   public static Page myPageHelper (Page page) {
        //list大小
        int size = page.getList().size();
        //开始下标，
        int start = (page.getPageNo() - 1) * page.getPageSize();
        //结束下标
        int end = start + page.getPageSize();
        //start 大于 sie直接返回null
        if (start > size){
            return null;
        }else
            //如果end 大于size就直接使用size
            if (end > size) {
                List list = page.getList().subList(start, size);
                page.setList(list);
                page.setTotal(size);
                page.setSize(list.size());
                return page;

            } else {
                List list = page.getList().subList(start, end);
                page.setList(list);
                page.setTotal(size);
                page.setSize(list.size());
                return page;
            }

    }
}
