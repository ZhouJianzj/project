package com.zj.monitorManager.service;

import com.zj.monitorManager.entity.Alarm;
import com.zj.monitorManager.entity.Sensor;
import com.zj.monitorManager.entity.SensorModel;
import com.zj.monitorManager.utils.ListMapUtil;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 模拟传感器发出警报，kafka - p
 *  1、构造初始化共享hashMap
 *  2、构造报警数据，定时任务线程池，目前模拟一个线程
 * @author zhoujian
 */
@Component
public class MessageSender {
    @Resource
    private  KafkaTemplate<String ,Object> kafkaTemplate;

    @Resource
    private AlarmService alarmService;

    /**
     * 发送消息，指定主题和消息
     * @param topicName 主题
     */
    public  void sendMessage(String topicName){

        //初始化共享的hashMap
        ListMapUtil.listToMap(alarmService.selectIPSMAAService());
        //获取数据中有哪些传感器
        List<Sensor> sensors =  alarmService.selectSensorAll();
        int size = sensors.size();

        ScheduledExecutorService pool = Executors.newScheduledThreadPool(4);
        //延时0秒之后每隔2秒重复执行一次
        pool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                //给所有的传感器生产报警信息
//                for (Sensor sensor :sensors){
                Sensor sensor = sensors.get(0);
                Alarm alarm = sensor.getAlarm();
                    alarm.setCurrentValue(String.valueOf(new Random().nextInt(100)));
                    alarm.setIsHandled(false);
                    alarm.setAlarmTime(new Date());
                    alarm.setSensorId(sensor.getId());

                    //获取传感器模型根据属性来判断随机生成的数据是否操作规定阈值
                    SensorModel sensorModel = sensor.getSensorModel();
                    int CurrentValue =  Integer.parseInt(alarm.getCurrentValue());
                    String dataPointName = sensorModel.getDataPointName();
                    if (sensorModel.getLowThreshold() < CurrentValue || sensorModel.getHighThreshold() > CurrentValue ){
                        alarm.setAlarmMsg(dataPointName + "异常！当前" + dataPointName + ":"+ CurrentValue);
                    }else {
                        alarm.setAlarmMsg(dataPointName + "正常！" + dataPointName + ":"+ CurrentValue);
                    }
                    sensor.setAlarm(alarm);
                    System.out.println("开始send消息....");
                    kafkaTemplate.send(topicName,sensor);
                    System.out.println("success");
                }
//            }
        },2,3, TimeUnit.SECONDS);

    }

}
