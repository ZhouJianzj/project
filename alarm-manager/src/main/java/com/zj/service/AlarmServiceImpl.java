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

    /**
     * 修改报警
     * @param alarm 修改的参数
     * @return 修改是否成功
     */
    @Override
    public Boolean modifyAlarm(Alarm alarm) {
        return alarmDao.modifyAlarm(alarm);
    }
}
