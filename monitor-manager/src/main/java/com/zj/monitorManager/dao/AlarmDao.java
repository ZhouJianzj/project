package com.zj.monitorManager.dao;


import com.zj.monitorManager.entity.Alarm;
import org.springframework.stereotype.Repository;

/**
 * @author zhoujian
 */
@Repository
public interface AlarmDao {

    Boolean insertAlarm(Alarm message);
}
