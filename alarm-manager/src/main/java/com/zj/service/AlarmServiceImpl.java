package com.zj.service;

import com.zj.dao.AlarmDao;
import com.zj.entity.Alarm;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhoujian
 */
@Service
public class AlarmServiceImpl implements AlarmService {
    @Resource
    private AlarmDao alarmDao;

    @Override
    public Boolean modifyAlarm(Alarm alarm) {
        return alarmDao.modifyAlarm(alarm);
    }
}
