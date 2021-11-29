package com.zj.monitorManager.sender;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author zhoujian
 */
@Component
public class MessageSender {
    @Resource
    private  KafkaTemplate<String ,Object> kafkaTemplate;

    public  void sendMessage(String topicName,Object message){
        //创建定时任务线程池
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(5);
        //延时1秒之后每隔2秒重复执行一次
        pool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("=====开始send消息=====");
                kafkaTemplate.send(topicName,message);
                System.out.println("======success======");
            }
        },1,2, TimeUnit.SECONDS);



//        延时1秒之后执行，只执行一次
//        pool.schedule(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("延迟执行");
//            }
//        },3, TimeUnit.SECONDS);

//        假设12点整执行第一次任务12:00，执行一次任务需要30min，下一次任务 12:30 + 3s 开始执
//        pool.scheduleWithFixedDelay(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(60000 * 30);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("" + new Date() +"重复执行2");
//            }
//        },1, 3, TimeUnit.SECONDS);
    }

}
