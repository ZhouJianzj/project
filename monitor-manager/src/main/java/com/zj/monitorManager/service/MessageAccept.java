package com.zj.monitorManager.service;

import com.zj.monitorManager.entity.Alarm;
import com.zj.monitorManager.entity.SensorModel;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author zhoujian
 */
@Component
public class MessageAccept {

    @Resource
    private AlarmService alarmService;

    /**
     * 消费消息
     * @param msg 根据控制台发现的kafka给消费者的对象，这里转换
     */
    @KafkaListener(topics="test",groupId = "gr01")
    public void acceptMessage(ConsumerRecord msg){
        //获取value强转为目标对象
        Alarm message = (Alarm) msg.value();
        System.out.println("接收到的消息为--------->" +  message);

        //根据传感器模型id查询传感器模型
        //根据message随机生成的CurrentValue，与查询所得的lowThreshold，highThreshold进行对比，
        // 如果比lowThreshold小 或者比highThreshold大则生成一个alarmMsg，进行数据库插入
        //如果在规定值以内则不往数据库里插入
        SensorModel sensorModel = alarmService.
                selectSensorModelById(message.getSensorModelId());
        if (Integer.parseInt(message.getCurrentValue()) > sensorModel.getHighThreshold()
                || Integer.parseInt(message.getCurrentValue()) < sensorModel.getLowThreshold()){
            message.setAlarmMsg(sensorModel.getDataPointName() + "异常！当前" +
                    sensorModel.getDataPointName() + "：" + message.getCurrentValue());
            Boolean insertAlarm = alarmService.insertAlarm(message);
        }

    }

}
