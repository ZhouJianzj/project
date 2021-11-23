package com.zj.controller;


import com.zj.entity.Alarm;
import com.zj.service.AlarmService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author zhoujian
 */
@RestController
@RequestMapping("alarm")
public class AlarmController {
    @Resource
    private AlarmService alarmService;

    /**
     * 添加报警信息
     * */
    @PostMapping("alarm")
    public Boolean insertAlarmController(@RequestBody Alarm alarm){
        return null;
    }

    /**
     * 修改报警
     * @param  alarm 修改的参数  使用到id 和 isHandled
     * @return 返回操作是否成功
     */
    @PutMapping("alarm")
    public Boolean modifyAlarmController(@RequestBody Alarm alarm){
        return alarmService.modifyAlarm(alarm);
    }
}
