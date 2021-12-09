package com.zj.controller;


import com.zj.entity.Alarm;
import com.zj.entity.History;
import com.zj.entity.Page;
import com.zj.service.AlarmService;
import com.zj.util.MyPageHelper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author zhoujian
 */
@RestController
@RequestMapping("alarm")
public class AlarmController {
    @Resource
    private AlarmService alarmService;

    /**
     * 修改报警
     * @param  alarm 修改的参数  使用到id 和 isHandled
     * @return 返回操作是否成功
     */
    @PutMapping("alarm")
    public Boolean modifyAlarmController(@RequestBody Alarm alarm){
        return alarmService.modifyAlarm(alarm);
    }

    /**
     * 查询报警
     * @param key 是否处理
     * @param from 开始时间
     * @param end 结束时间
     * @param pageNo 页面
     * @param pageSize 数据量
     * @return 返回的结果集
     */
    @GetMapping("alarm")
    public Page<Alarm> findAlarmController(@RequestParam("key") Boolean key,
                                           @RequestParam("from") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date from,
                                           @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date end,
                                       @RequestParam(value = "pageNo",defaultValue = "1") String pageNo,
                                       @RequestParam(value = "pageSize",defaultValue = "8")String pageSize){

        return MyPageHelper.myPageHelper(new Page(alarmService.findAlarmService(key,from,end)
                ,Integer.parseInt(pageNo)
                ,Integer.parseInt(pageSize)));

    }

    /**
     * 查询历史报警记录
     * */
    @GetMapping("history")
    public Page<History> selectHistoryController(@RequestParam("key") Boolean key,
                                                 @RequestParam("from") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date from,
                                                 @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date end,
                                                 @RequestParam(value = "pageNo",defaultValue = "1") String pageNo,
                                                 @RequestParam(value = "pageSize",defaultValue = "8")String pageSize){
        return MyPageHelper.myPageHelper(new Page(alarmService.findHistoryService(key,from,end)
                ,Integer.parseInt(pageNo)
                ,Integer.parseInt(pageSize)));

    }

}
