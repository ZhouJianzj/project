package com.zj.monitorManager.dao;


import com.zj.monitorManager.entity.Alarm;
import com.zj.monitorManager.entity.Pipe;
import com.zj.monitorManager.entity.SensorModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhoujian
 */
@Repository
public interface AlarmDao {

    Boolean insertAlarm(Alarm message);

    List<Pipe> selectPipeMoreMessage(String itemID);

    SensorModel selectSensorModelById(int sensorModelId);
}
