package com.zj.monitorManager.web;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhoujian
 */
@Component
@ServerEndpoint("/webSocket")
public class WebSocket {
    private static int onlineCount = 0;
    private static Map<Integer, WebSocket> clients = new ConcurrentHashMap<Integer, WebSocket>();
    private Session session;

    /**
     *建立连接的时候被调用
     */
    @OnOpen
    public void onOpen( Session session) throws IOException {
        this.session = session;
        addOnlineCount();
        clients.put(WebSocket.onlineCount,this);
        System.out.println("已连接");
    }

    /**
     * 接收到客户端数据的时候被调用
     * @param message 客户端发送来的数据
     */
    @OnMessage
    public void onMessage(String message) throws IOException {
        JSONObject jsonTo = JSONObject.parseObject(message);

        System.out.println(jsonTo.getString("to") +":"+ jsonTo.getString("msg"));

        if (!jsonTo.getString("to").toLowerCase().equals("all")){
            sendMessageTo(jsonTo.getString("msg"), jsonTo.getString("to"));
        }else{
            sendMessageAll(jsonTo.getString("msg"));
        }
    }

    /**
     * 连接关闭的时候被调用的
     */
    @OnClose
    public void onClose() throws IOException {
//        clients.remove(username);
        subOnlineCount();
    }


    /**
     * 发生错误的时候执行的
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }






    public void sendMessageTo(String message, String To) throws IOException {
        //session.getBasicRemote().sendText(message);
        //session.getAsyncRemote().sendText(message);
        for (WebSocket item : clients.values()) {
//            if (item.username.equals(To) ){
                item.session.getAsyncRemote().sendText(message);
//            }

        }
    }

    public void sendMessageAll(String message) throws IOException {
        for (WebSocket item : clients.values()) {
            item.session.getAsyncRemote().sendText(message);
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocket.onlineCount--;
    }

    public static synchronized Map<Integer, WebSocket> getClients() {
        return clients;
    }

}