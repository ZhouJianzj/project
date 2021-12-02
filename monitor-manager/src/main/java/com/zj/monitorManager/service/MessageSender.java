package com.zj.monitorManager.service;

import com.zj.monitorManager.entity.Alarm;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * 实现消息生产也就是发送
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
     * @param message 消息
     */
    public  void sendMessage(String topicName, Alarm message){
        //创建定时任务线程池
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(5);


//        //延时0秒之后每隔2秒重复执行一次
//        pool.scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
//                //随机获取一个传感器
//                Sensor sensor = sensors.get((int) (Math.random()*sensorCount));
//                //随机生产一个报警信息
//                message.setSensor(sensor);
//                message.setIsHandled(false);
//                message.setAlarmTime(new Date());
//                message.setCurrentValue(String.valueOf((int) ((Math.random()*101))));
//
//                System.out.println("开始send消息....");
//                kafkaTemplate.send(topicName,message);
//                System.out.println("success");
//            }
//        },0,5, TimeUnit.SECONDS);
//
    }

}
