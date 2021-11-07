package com.zj.util;

import com.zj.entity.Page;

import java.util.List;

/**
 * @author zhoujian
 */
public class MyPageHelper {

   public static List myPageHelper (Page page) {
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

                return page.getList().subList(start, size);

            } else {
                return page.getList().subList(start,end);
            }

    }
}
