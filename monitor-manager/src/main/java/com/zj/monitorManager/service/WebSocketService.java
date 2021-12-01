package com.zj.monitorManager.service;

import com.zj.monitorManager.config.ServerEncoder;
import com.zj.monitorManager.entity.Alarm;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * @author zhoujian
 */


@ServerEndpoint(value = "/alarm",encoders = {ServerEncoder.class})
@RestController
public class WebSocketService {

    public static Boolean isConnected = false;

    public static Session session = null;


    /**
     * 建立连接。
     * 建立连接时入参为session
     */
    @OnOpen
    public void onOpen(Session s){
        session = s;
        isConnected = true;
        System.out.println("建立了连接"+ session.getId());
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
     * 接收前端传过来的数据。
     * 虽然在实现推送逻辑中并不需要接收前端数据，但是作为一个webSocket的教程或叫备忘，还是将接收数据的逻辑加上了。
     */
    @OnMessage
    public void onMessage( String message){
        System.out.println(message + "------from------" + session.getId());
//        测试是否可以想客户端发送消息
//        try {
//            sendMessage();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * 服务器主动的发送消息到客户端
     */
    public void sendMessage(Alarm alarm) throws IOException {
//            session.getBasicRemote().sendText(alarm);
        try {
            session.getBasicRemote().sendObject(alarm);
        } catch (EncodeException e) {
            e.printStackTrace();
        }
    }
}
