package com.zj.controller;



import com.zj.service.AlarmService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhoujian
 */
@RestController
@RequestMapping("alarm")
public class AlarmController {
    @Resource
    private AlarmService alarmService;

    public Boolean modifyAlarm(){
        return alarmService.modifyAlarm();
    }
}
