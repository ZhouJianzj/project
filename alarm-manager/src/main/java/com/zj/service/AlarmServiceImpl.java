package com.zj.service;

import com.zj.dao.AlarmDao;
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
    public Boolean modifyAlarm() {
        return alarmDao.modifyAlarm();
    }
}
