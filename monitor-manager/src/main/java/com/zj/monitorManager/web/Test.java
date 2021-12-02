package com.zj.monitorManager.web;

import com.zj.monitorManager.entity.Pipe;
import com.zj.monitorManager.service.AlarmService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class Test {
    @Resource
    AlarmService alarmService;
    @GetMapping("pipe")
    public List<Pipe> test(String itemId){
        return alarmService.selectPipeMoreMessage(itemId);
    }
}
