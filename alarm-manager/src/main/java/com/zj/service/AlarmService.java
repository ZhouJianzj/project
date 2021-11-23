package com.zj.service;

import com.zj.entity.Alarm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author zhoujian
 */
@Service
public interface AlarmService{


    public Boolean modifyAlarm(Alarm alarm);

    List<Alarm> findAlarmService(Boolean key, Date from, Date end);
}
