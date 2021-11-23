package com.zj.dao;

import com.zj.entity.Alarm;
import org.springframework.stereotype.Repository;

/**
 * @author zhoujian
 */
@Repository
public interface AlarmDao {
    Boolean modifyAlarm(Alarm alarm);
}
