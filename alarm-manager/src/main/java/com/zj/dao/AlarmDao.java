package com.zj.dao;

import com.zj.entity.Alarm;
import com.zj.entity.Sensor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhoujian
 */
@Repository
public interface AlarmDao {
    Boolean modifyAlarm(Alarm alarm);

    Boolean alarmInsert(Alarm alarm);

    List<Sensor> sensorIdSelect(String sensorId);
}
