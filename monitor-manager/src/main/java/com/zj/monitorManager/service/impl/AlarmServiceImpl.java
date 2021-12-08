package com.zj.monitorManager.service.impl;

import com.zj.monitorManager.dao.AlarmDao;
import com.zj.monitorManager.entity.*;
import com.zj.monitorManager.service.AlarmService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoujian
 */
@Service
public class AlarmServiceImpl  implements AlarmService {
    @Resource
    private AlarmDao alarmDao;

    @Override
    public Boolean insertAlarm(Alarm message) {
        return alarmDao.insertAlarm(message);
    }

    @Override
    public SensorModel selectSensorModelById(int sensorModelId) {
        return alarmDao.selectSensorModelById(sensorModelId);
    }

    /**
     * 先查询出所有的item对应的管道
     * 方法中对数据进行了二次处理
     * item
     *    pipe
     *      pipeModel
     *      sensor
     *          sensorModel
     *          alarm
     */
    @Override
    public List<Item> selectIPSMAAService() {
        List<Item> items = alarmDao.selectIPSMAADao();
        ArrayList<String> strings = new ArrayList<>(3);
        for (Item item:items){
            List<Pipe> pipes = item.getPipes();
            for (Pipe pipe : pipes){
                    strings.clear();
                    strings.add(pipe.getPipeModel().getPipeIntroduce());
                    strings.add(pipe.getPipeModel().getPipePic());
                    strings.add(pipe.getPipeModel().getPipeManual());
                    for (String string : strings){
                        int e = string.indexOf("matter");
                        int lastIndexOf = string.lastIndexOf("/");
                        pipe.getPipeModel().getFileRelativePath().add(string.substring(e));
                        pipe.getPipeModel().getFileName().add(string.substring(lastIndexOf + 1));
                    }
            }
        }
        return items;
    }


    /**
     * 查询所有的传感器以及附带的信息
     * @return
     */
    @Override
    public List<Sensor> selectSensorAll() {
        List<Sensor> sensors =  alarmDao.selectSensorAll();
        return sensors;
    }

    /**
     * 所有报警数据插入数据
     * @param alarm
     * @return
     */
    @Override
    public boolean insertAlarmHistory(List alarms) {
        return alarmDao.insertAlarmHistory(alarms);
    }


}
