package com.zj.monitorManager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author zhoujian
 */
@Configuration
public class WebSocketConfiguration {

    /**
     *
     * 为了扫描这个注解的 @ServerEndpoint
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
