package com.zj.monitorManager.service.impl;

import com.zj.monitorManager.dao.AlarmDao;
import com.zj.monitorManager.entity.Alarm;
import com.zj.monitorManager.entity.Pipe;
import com.zj.monitorManager.entity.SensorModel;
import com.zj.monitorManager.service.AlarmService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhoujian
 */
@Service
public class AlarmServiceImpl  implements AlarmService {
    @Resource
    private AlarmDao alarmDao;

    @Override
    public Boolean insertAlarm(Alarm message) {
        return alarmDao.insertAlarm(message);
    }

    @Override
    public List<Pipe> selectPipeMoreMessage(String itemId) {
        return alarmDao.selectPipeMoreMessage(itemId);
    }

    @Override
    public SensorModel selectSensorModelById(int sensorModelId) {
        return alarmDao.selectSensorModelById(sensorModelId);
    }

}
