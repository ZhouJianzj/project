package com.zj.monitorManager.service;

import com.zj.monitorManager.entity.Sensor;
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

    @Resource
    private WebSocketService webSocketService;

    /**
     * 消费消息
     * @param msg 根据控制台发现的kafka给消费者的对象，这里转换
     */
    @KafkaListener(topics="pipe",groupId = "gr01")
    public void acceptMessage(ConsumerRecord msg){
        Sensor value = (Sensor) msg.value();
        System.out.println("value---------------->" + value);
//        //获取value强转为目标对象
//        Alarm message = (Alarm) msg.value();
//        System.out.println("接收到的消息为--------->" +  message);
//        //webSocket推送有问题的数据
//        if (WebSocketService.isConnected){
//            try {
//                webSocketService.sendMessage(message);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }


//            //webSocket推送有问题的数据
//            if (WebSocketService.isConnected){
//                try {
//                    webSocketService.sendMessage(message);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            //异常数据插入数据库
//            alarmService.insertAlarm(message);
//        }
    }

}
