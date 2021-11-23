package com.zj.service;

import com.zj.entity.Alarm;
import org.springframework.stereotype.Service;

/**
 * @author zhoujian
 */
@Service
public interface AlarmService{


    public Boolean modifyAlarm(Alarm alarm);
}
