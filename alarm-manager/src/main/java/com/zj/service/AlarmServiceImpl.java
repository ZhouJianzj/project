package com.zj.service;

import com.zj.dao.AlarmDao;
import com.zj.entity.Alarm;
import com.zj.entity.CommonResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author zhoujian
 */
@Service
public class AlarmServiceImpl implements AlarmService {
    @Resource
    private AlarmDao alarmDao;

    /**
     * 修改报警
     * @param alarm 修改的参数
     * @return 修改是否成功
     */
    @Override
    public Boolean modifyAlarm(Alarm alarm) {
        return alarmDao.modifyAlarm(alarm);
    }

    /**
     * 查询报警
     * @param key 是否处理过
     * @param from 开始时间
     * @param end 结束时间
     * @return 结果集
     */
    @Override
    public List<Alarm> findAlarmService(Boolean key, Date from, Date end) {

        return alarmDao.selectAlarmDao(key,from,end);
    }

    @Override
    public List findHistoryService(Boolean key, Date from, Date end) {
        return alarmDao.historySelect(key,from,end);
    }

}
