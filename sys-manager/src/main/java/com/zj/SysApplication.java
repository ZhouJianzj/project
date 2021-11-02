package com.zj;


import com.zj.util.Md5;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.data.redis.config.annotation.web.server.EnableRedisWebSession;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;


/**
 * @author zhoujian
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableRedisHttpSession
@MapperScan("com.zj.dao")
public class SysApplication {
    public static void main(String[] args) {
        SpringApplication.run(SysApplication.class,args);
    }

}


