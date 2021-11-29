package com.zj.monitorManager.web;


import com.zj.monitorManager.entity.Alarm;
import com.zj.monitorManager.service.AlarmService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class Test {
    @Resource
    private AlarmService alarmService;

    @PostMapping("test")
    public Boolean test(@RequestBody Alarm alarm) {
        return alarmService.insertAlarm(alarm);
    }
}
