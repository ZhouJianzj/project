package com.zj.service;

import com.zj.dao.AlarmDao;
import com.zj.entity.Alarm;
import com.zj.entity.CommonResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
     * 添加报警信息
     * @param alarm
     * @return 新增是否成功
     * */
    @Override
    public CommonResponse<Boolean> insertAlarmService(Alarm alarm) {
        CommonResponse<Boolean> response = new CommonResponse<>();
        String sensorId = alarm.getSensor().getId();
        if (alarmDao.sensorIdSelect(sensorId) != null && alarmDao.sensorIdSelect(sensorId).size() != 0){
            if (alarmDao.alarmInsert(alarm)){
                response.setMsg("插入成功");
                response.setStatus(200);
            }else {
                response.setMsg("插入失败");
                response.setStatus(400);
            }
        }else {
            response.setMsg("不存在sensor_id为：" + sensorId + " 的数据");
            response.setStatus(400);
        }

        return response;
    }
}
