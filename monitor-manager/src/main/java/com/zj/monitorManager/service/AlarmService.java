package com.zj.monitorManager.service;

import com.zj.monitorManager.entity.Alarm;
import com.zj.monitorManager.entity.Pipe;
import com.zj.monitorManager.entity.SensorModel;

import java.util.List;

/**
 * @author zhoujian
 */
public interface AlarmService {

     Boolean insertAlarm(Alarm message) ;

     List<Pipe> selectPipeMoreMessage(String itemId);

     SensorModel selectSensorModelById(int sensorModelId);

}
