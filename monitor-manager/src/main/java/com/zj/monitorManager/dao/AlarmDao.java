package com.zj.monitorManager.dao;


import com.zj.monitorManager.entity.Alarm;
import com.zj.monitorManager.entity.Item;
import com.zj.monitorManager.entity.Sensor;
import com.zj.monitorManager.entity.SensorModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhoujian
 */
@Repository
public interface AlarmDao {

    Boolean insertAlarm(Alarm message);

    SensorModel selectSensorModelById(int sensorModelId);

    List<Item> selectIPSMAADao();

    List<Sensor> selectSensorAll();

    boolean insertAlarmHistory(@Param("alarms") List alarms);
}
