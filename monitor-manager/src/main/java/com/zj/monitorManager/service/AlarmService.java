package com.zj.monitorManager.service;

import com.zj.monitorManager.entity.Alarm;
import com.zj.monitorManager.entity.Item;
import com.zj.monitorManager.entity.Sensor;
import com.zj.monitorManager.entity.SensorModel;

import java.util.List;

/**
 * @author zhoujian
 */
public interface AlarmService {

     Boolean insertAlarm(Alarm message) ;

     SensorModel selectSensorModelById(int sensorModelId);

     List<Item> selectIPSMAAService();

     List<Sensor> selectSensorAll();


    boolean insertAlarmHistory(List alarms);
}
