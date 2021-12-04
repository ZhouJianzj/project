package com.zj.monitorManager;

import com.zj.monitorManager.service.MessageSender;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 *
 * @author zhoujian
 */
@Component
public class MyApplicationRunner implements ApplicationRunner {
    @Resource
    private MessageSender messageSender;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("+++++++++++++++++在springboot启动之后 启动线程池模拟kafka生产报警信息++++++++++++++++");
        messageSender.sendMessage("pipe");
    }
}
