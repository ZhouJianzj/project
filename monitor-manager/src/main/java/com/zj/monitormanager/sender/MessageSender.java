package com.zj.monitormanager.sender;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author zhoujian
 */
@Component
public class MessageSender {
    @Resource
    private KafkaTemplate<String ,Object> kafkaTemplate;

    public void sendMessage(String topicName,Object message){
        kafkaTemplate.send(topicName,message);
    }
}
