package com.zj.monitorManager;

import com.zj.monitorManager.sender.MessageSender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhoujian
 */
@SpringBootApplication
public class MonitorManagerApplication {


    public static void main(String[] args) {
        SpringApplication.run(MonitorManagerApplication.class, args);
        new MessageSender().test();
    }

}
