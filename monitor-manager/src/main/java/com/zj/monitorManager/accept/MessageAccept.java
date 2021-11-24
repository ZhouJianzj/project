package com.zj.monitorManager.accept;

import com.zj.monitorManager.entity.Message;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author zhoujian
 */
@Component
public class MessageAccept {

    @KafkaListener(topics = "pipe",groupId = "1")
    public void onMessage(Message message){
        System.out.println(message);
    }
}
