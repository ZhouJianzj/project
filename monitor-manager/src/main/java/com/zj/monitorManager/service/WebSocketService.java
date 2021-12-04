package com.zj.monitorManager.service;

import com.zj.monitorManager.config.ServerEncoder;
import com.zj.monitorManager.entity.Sensor;
import com.zj.monitorManager.utils.ListMapUtil;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author zhoujian
 */


@ServerEndpoint(value = "/alarm/{type}",encoders = {ServerEncoder.class})
@RestController
public class WebSocketService {



    public  Session session = null;

    private String type = null;

    public  Boolean sendAllAlarm = false;

    /**
     * 保存多个连接的实现，前端多个连接的展示
     */
    public static ConcurrentHashMap<String, WebSocketService> concurrentHashMap = new ConcurrentHashMap<>();


//    /**
//     * 引入自己的接口类，注意要加上static 静态修饰
//     */
//    private static AlarmService alarmService;
//
//    /**
//     * mobileUserService的set方法
//     */
//    @Autowired
//    public  void setApplicationContext( AlarmService alarmService) {
//        WebSocketService.alarmService= alarmService;
//    }


    /**
     * 建立连接。
     * 建立连接时入参为session
     */
    @OnOpen
    public void onOpen( @PathParam("type") String type, Session session){

        //建立一次连接保存一个对象
        this.session = session;
        this.type = type;
        if (type.equals("alarms")){
            sendAllAlarm = true;
        }
        concurrentHashMap.put(this.type,this);
        System.out.println("连接连接type为：==============="+ type );
    }

    /**
     * 关闭连接
     */
    @OnClose
    public void onClose(){

        //如果关闭了就移除当前的连接对象
        concurrentHashMap.remove(this.type);
        System.out.println("连接关闭"+ session.getId());
    }

    /**
     * 接收前端传过来的数据,发送实时的数据给前端
     */
    @OnMessage
    public void onMessage(String ItemId){
        if (type.equals("item")){
            try {
                sendMessage(ItemId);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 服务器主动的发送消息到建立item连接的客户端，获取指定itemId的item
     */
    public void sendMessage(String itemId) throws IOException {

            //直接在共享的hashMap中查询
            ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
            pool.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    HashMap<String, HashMap<String, Object>> stringHashMapHashMap = ListMapUtil.hashMapA.get(itemId);
                    try {
                        session.getBasicRemote().sendObject(stringHashMapHashMap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (EncodeException e) {
                        e.printStackTrace();
                    }
                }
            }, 2, 3, TimeUnit.SECONDS);

    }

    /**
     * 服务器主动的发送消息到建立alarms连接的客户端，一个一个的发事实的报警消息
     */
    public void sendMessage(Sensor sensor) throws IOException {
        try {
            session.getBasicRemote().sendObject(sensor);
        } catch (EncodeException e) {
            e.printStackTrace();
        }
    }

}
