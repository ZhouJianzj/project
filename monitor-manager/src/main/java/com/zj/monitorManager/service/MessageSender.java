package com.zj.monitorManager.service;

import com.zj.monitorManager.entity.Alarm;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 实现消息生产也就是发送
 * @author zhoujian
 */
@Component
public class MessageSender {
    @Resource
    private  KafkaTemplate<String ,Object> kafkaTemplate;

    /**
     * 发送消息，指定主题和消息
     * @param topicName 主题
     * @param message 消息
     *
     *  这里模拟  温度传感器  和   压力传感器
     *       1、随机生产的报警中的sensorId要真实存在，
     *           温度传感器 id = 1、压力传感器 id = 2
     *       2、需要生产currentValue、alarmMsg、alarmTime
     */
    public  void sendMessage(String topicName, Alarm message){
        //创建定时任务线程池
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(5);
        //延时0秒之后每隔2秒重复执行一次
        pool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                //生产不同传感器的不同报警消息
                //随机生成传感器id为 1或者2
                message.setSensorId((int) ((Math.random()*2) + 1));
                if (message.getSensorId() == 1){
                    //温度传感器 0-100°
                    message.setCurrentValue(String.valueOf((int) ((Math.random()*101))));
                    message.setIsHandled(false);
                    message.setAlarmTime(new Date());
                }
                if (message.getSensorId() == 1){
                    //压力感器 0-100Mpa
                    message.setCurrentValue(String.valueOf((int) ((Math.random()*101))));
                    message.setIsHandled(false);
                    message.setAlarmTime(new Date());
                }
                System.out.println("开始send消息....");
                kafkaTemplate.send(topicName,message);
                System.out.println("success");
            }
        },0,5, TimeUnit.SECONDS);

    }

}
