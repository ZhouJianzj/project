package com.zj.monitorManager.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * @author zhoujian
 */

@Configuration
public class KafkaConfiguration {
    @Bean
    public NewTopic newTopic(){
        return TopicBuilder.name("pipe").build();
    }
}
