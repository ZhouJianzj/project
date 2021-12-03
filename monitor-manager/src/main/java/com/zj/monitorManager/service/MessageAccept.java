package com.zj.monitorManager.service;

import com.zj.monitorManager.entity.Sensor;
import com.zj.monitorManager.utils.ListMapUtil;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;

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
        //获取value强转为目标对象
        Sensor sensor = (Sensor) msg.value();

        //获取共享haspMap
        HashMap<String, HashMap<String, HashMap<String, Object>>> hashMapA = ListMapUtil.hashMapA;

        System.out.println("sensor---------------->" + sensor);

        //往共享hashMap中存随机生成的sensor报警信息
        ListMapUtil.forShareHashMap(hashMapA,sensor);


        //webSocket推送有问题的数据
//            if (WebSocketService.isConnected){
//                try {
//                    webSocketService.sendMessage(message);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }

        //异常数据插入数据库
        sensor.getAlarm().setSensorId(sensor.getId());
        alarmService.insertAlarm(sensor.getAlarm());
    }

}
