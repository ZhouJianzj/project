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

    /**
     * 消费消息
     * @param msg 根据控制台发现的kafka给消费者的对象，这里转换
     */
    @KafkaListener(topics="pipe",groupId = "gr01")
    public void acceptMessage(ConsumerRecord msg){
        //获取value强转为目标对象
        Sensor sensor = (Sensor) msg.value();
        System.out.println("消费的sensor---------------->" + sensor.getAlarm() );
//        //获取webSocket连接对象
//        WebSocketService alarmConnection = WebSocketService.concurrentHashMap.get("alarms");
//        System.out.println("==============alarms连接=======" +alarmConnection + "==========");
//        if (alarmConnection != null) {
//                try {
//                    alarmConnection.sendMessage(sensor);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//        }
//        //获取共享haspMap
//        HashMap<String, HashMap<String, HashMap<String, Object>>> hashMapA = ListMapUtil.hashMapA;
//        System.out.println("消费的sensor---------------->" + sensor );
//        //往共享hashMap中存随机生成的sensor报警信息
//        ListMapUtil.updateShareHashMap(hashMapA,sensor);
//
//        //异常数据插入数据库
//        int currentValue = Integer.parseInt(sensor.getAlarm().getCurrentValue());
//        SensorModel sensorModel = sensor.getSensorModel();
//        if (currentValue < sensorModel.getLowThreshold() || currentValue > sensorModel.getHighThreshold() ){
//            sensor.getAlarm().setSensorId(sensor.getId());
//            alarmService.insertAlarm(sensor.getAlarm());
//        }


    }
}
