package com.zj.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.data.redis.config.annotation.web.server.EnableRedisWebSession;

/**
 * @author zhoujian
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GatewagApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewagApplication.class, args);
    }

}
