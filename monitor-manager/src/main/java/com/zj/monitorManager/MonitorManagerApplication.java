package com.zj.monitorManager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhoujian
 */
@SpringBootApplication
@MapperScan("com.zj.monitorManager.dao")
public class MonitorManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonitorManagerApplication.class, args);
    }

}
