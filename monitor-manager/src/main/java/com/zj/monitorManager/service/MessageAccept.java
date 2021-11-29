package com.zj.monitorManager.service;

import com.zj.monitorManager.entity.Alarm;
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

        //如果是温度传感器并且温度大于75°的时候就报警就写入到数据库
//        if (message.getSensorId() == 1 &&
//                Integer.parseInt(message.getCurrentValue()) > 75){
//            message.setAlarmMsg("温度异常！当前温度:" + message.getCurrentValue() + "°");
//            Boolean b =  alarmService.insertAlarm(message);
//        }

    }

}
