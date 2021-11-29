package com.zj.monitorManager;

import com.zj.monitorManager.sender.MessageSender;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 *
 * 在springboot启动之后，执行特定的代码
 * @author zhoujian
 */
@Component
public class MyApplicationRunner implements ApplicationRunner {
    @Resource
    private MessageSender messageSender;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("在springboot启动之后，执行");
        messageSender.sendMessage("test","message from java !");
    }
}
