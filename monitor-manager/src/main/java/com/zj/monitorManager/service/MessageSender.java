package com.zj.monitorManager.service;

import com.zj.monitorManager.entity.Alarm;
import com.zj.monitorManager.entity.Sensor;
import com.zj.monitorManager.entity.SensorModel;
import com.zj.monitorManager.utils.ListMapUtil;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
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

    public static ScheduledExecutorService pool = Executors.newScheduledThreadPool(5);

    WebSocketService alarmConnection = WebSocketService.concurrentHashMap.get("alarms");

    /**
     * 发送消息，指定主题和消息
     * @param topicName 主题
     */
    public  void produceMessage(String topicName){
        //初始化共享的hashMap
       ListMapUtil.listToMap(alarmService.selectIPSMAAService());
        //获取数据中有哪些传感器
        List<Sensor> sensors =  alarmService.selectSensorAll();
        int size = sensors.size();
        //延时0秒之后每隔2秒重复执行一次
        pool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                //随机获取一个传感器生产报警信息
                Sensor sensor = sensors.get(new Random().nextInt(size));
                Alarm alarm = new Alarm();
                alarm.setCurrentValue(String.valueOf(new Random().nextInt(101)));
                    alarm.setIsHandled(false);
                    alarm.setAlarmTime(new Date());
                    alarm.setSensorId(sensor.getId());
                    //获取传感器模型根据属性来判断随机生成的数据是否操作规定阈值
                    SensorModel sensorModel = sensor.getSensorModel();
                    int CurrentValue =  Integer.parseInt(alarm.getCurrentValue());
                    String dataPointName = sensorModel.getDataPointName();
                    if (CurrentValue < sensorModel.getLowThreshold() || CurrentValue > sensorModel.getHighThreshold() ){
                        alarm.setAlarmMsg(dataPointName + "异常！当前" + dataPointName + ":"+ CurrentValue);
                        alarmService.insertAlarm(alarm);
                    }else {
                        alarm.setAlarmMsg(dataPointName + "正常！当前" + dataPointName + ":"+ CurrentValue);
                    }
                    sensor.setAlarm(alarm);

                    //获取webSocket连接对象
                    WebSocketService alarmConnection = WebSocketService.concurrentHashMap.get("alarms");
                    System.out.println("==============alarms连接=======" +alarmConnection + "==========");
                    if (alarmConnection != null) {
                        try {
                            alarmConnection.sendMessage(sensor);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                   //获取共享haspMap
                    HashMap<String, HashMap<String, HashMap<String, Object>>> hashMapA = ListMapUtil.hashMapA;
                   //往共享hashMap中存随机生成的sensor报警信息
                   ListMapUtil.updateShareHashMap(hashMapA,sensor);

                    System.out.println("开始send消息....");
                    kafkaTemplate.send(topicName,sensor);
                    System.out.println("success");
                }
        },2,3, TimeUnit.SECONDS);
    }
}
