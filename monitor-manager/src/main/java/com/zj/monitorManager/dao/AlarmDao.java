package com.zj.monitorManager.dao;



import com.zj.monitorManager.entity.Alarm;
import com.zj.monitorManager.entity.Sensor;
import com.zj.monitorManager.entity.SensorModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhoujian
 */
@Repository
public interface AlarmDao {

    Boolean insertAlarm(Alarm message);

    List<SensorModel> selectSensorModel();

    List<Sensor> selectSensor();

    SensorModel selectSensorModelById(int sensorModelId);

    Sensor selectSensorById(int sensorId);
}
