package com.zj.dao;

import com.zj.entity.Alarm;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author zhoujian
 */
@Repository
public interface AlarmDao {
    Boolean modifyAlarm(Alarm alarm);

    List<Alarm> selectAlarmDao(Boolean key, Date from,Date end);
}
