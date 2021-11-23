package com.zj.controller;



import com.zj.entity.Alarm;
import com.zj.entity.CommonResponse;
import com.zj.service.AlarmService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    /**
     * 添加报警信息
     * */
    @PostMapping("alarm")
    public CommonResponse<Boolean> insertAlarmController(@RequestBody Alarm alarm){
        return null;
    }
}
