package com.zj.monitorManager.service;

import com.zj.monitorManager.entity.Alarm;
import com.zj.monitorManager.entity.Sensor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoujian
 */
@Component
public class MessageAccept {

    @Resource
    private AlarmService alarmService;

    private static List alarms = new ArrayList(10);

    /**
     * 消费消息
     * @param msg 根据控制台发现的kafka给消费者的对象，这里转换
     */
    @KafkaListener(topics="pipe",groupId = "gr01")
    public void acceptMessage(ConsumerRecord msg){
        //获取value强转为目标对象
        Sensor sensor = (Sensor) msg.value();
        Alarm alarm = sensor.getAlarm();
        alarms.add(alarm);
        if (alarms.size() == 20){
            System.out.println("************************开始插入20条*********************");
            alarmService.insertAlarmHistory(alarms);
            alarms.clear();
        }


    }
}
