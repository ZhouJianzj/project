package com.zj.monitorManager.service;

import com.zj.monitorManager.entity.Alarm;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author zhoujian
 */
@Component
public class MessageAccept {

    @Resource
    private AlarmService alarmService;

    @Resource
    private WebSocketService webSocketService;

    /**
     * 消费消息
     * @param msg 根据控制台发现的kafka给消费者的对象，这里转换
     */
    @KafkaListener(topics="test",groupId = "gr01")
    public void acceptMessage(ConsumerRecord msg){
        //获取value强转为目标对象
        Alarm message = (Alarm) msg.value();
        System.out.println("接收到的消息为--------->" +  message);


        if (
            //获取报警信息中的传感器中的传感器模型报警值的最高指标
            Integer.parseInt(message.getCurrentValue()) > message.getSensor().getSensorModel().getHighThreshold()
            ||
            //获取报警信息中的传感器中的传感器模型报警值的最低指标
            Integer.parseInt(message.getCurrentValue()) < message.getSensor().getSensorModel().getLowThreshold())
        {
            String dataPointName = message.getSensor().getSensorModel().getDataPointName();
            message.setAlarmMsg(
                    dataPointName + "异常!当前" + dataPointName + ":" +
                    message.getCurrentValue());
            message.setSensorId(message.getSensor().getId());

            //webSocket推送有问题的数据
            if (WebSocketService.isConnected){
                try {
                    webSocketService.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //异常数据插入数据库
            alarmService.insertAlarm(message);
        }
    }

}
