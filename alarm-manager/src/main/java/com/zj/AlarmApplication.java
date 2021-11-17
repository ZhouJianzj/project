package com.zj;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author zhoujian
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableRedisHttpSession
@MapperScan("com.zj.dao")
public class AlarmApplication {
    public static void main(String[] args) {
        SpringApplication.run(AlarmApplication.class ,args);
    }
}
