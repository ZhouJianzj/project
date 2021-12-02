package com.zj.monitorManager.service;

import com.zj.monitorManager.config.ServerEncoder;
import com.zj.monitorManager.entity.Alarm;
import com.zj.monitorManager.utils.ListMapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author zhoujian
 */


@ServerEndpoint(value = "/alarm",encoders = {ServerEncoder.class})
@RestController
public class WebSocketService {

    public static Boolean isConnected = false;

    public static Session session = null;

    /**
     * 引入自己的接口类，注意要加上static 静态修饰
     */
    private static AlarmService alarmService;

    /**
     * mobileUserService的set方法
     */
    @Autowired
    public  void setApplicationContext( AlarmService alarmService) {
        WebSocketService.alarmService= alarmService;
    }


    /**
     * 建立连接。
     * 建立连接时入参为session
     */
    @OnOpen
    public void onOpen(Session s){
        session = s;
        isConnected = true;
        System.out.println("建立了连接"+ session.getId() );
    }

    /**
     * 关闭连接
     */
    @OnClose
    public void onClose(){
        System.out.println("连接关闭"+ session.getId());
        isConnected = false;
        session = null;

    }

    /**
     * 接收前端传过来的数据,发送事实的数据给前端
     */
    @OnMessage
    public void onMessage(String message){
        try {
           while (isConnected){
               sendMessage(message);
           }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 服务器主动的发送消息到客户端，获取指定itemId的item
     */
    public void sendMessage(String itemId) throws IOException {
        try {
            //直接在共享的hashMap中查询
            HashMap<String, HashMap<String, Object>> stringHashMapHashMap = ListMapUtil.hashMapA.get(itemId);
            session.getBasicRemote().sendObject(stringHashMapHashMap);
        } catch (EncodeException e) {
            e.printStackTrace();
        }
    }

    /**
     * 摒弃了
     * 服务器主动的发送消息到客户端，一个一个的发
     */
    public void sendMessage(Alarm alarm) throws IOException {
        try {
            session.getBasicRemote().sendObject(alarm);
        } catch (EncodeException e) {
            e.printStackTrace();
        }
    }

}
