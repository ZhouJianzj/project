package com.zj.service;

import com.zj.entity.Alarm;
import com.zj.entity.CommonResponse;
import org.springframework.stereotype.Service;

/**
 * @author zhoujian
 */
@Service
public interface AlarmService{


    public Boolean modifyAlarm(Alarm alarm);

    CommonResponse<Boolean> insertAlarmService(Alarm alarm);
}
