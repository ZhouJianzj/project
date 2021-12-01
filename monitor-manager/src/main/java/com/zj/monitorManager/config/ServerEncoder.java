package com.zj.monitorManager.config;


import com.alibaba.fastjson.JSONArray;
import com.zj.monitorManager.entity.Alarm;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * Text<ResponseMessage>里的ResponseMessage是我自己写的一个消息类
 * 如果你写了一个名叫Student的类，需要通过sendObject()方法发送，那么这里就是Text<Student>
 * @author zhoujian
 */
public class ServerEncoder implements Encoder.Text<Alarm> {

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
        // 这里不重要
    }

    @Override
    public void init(EndpointConfig arg0) {
        // TODO Auto-generated method stub
        // 这里也不重要

    }

    /**
     *  encode()方法里的参数和Text<T>里的T一致，如果你是Student，这里就是encode（Student student）
     */
    @Override
    public String encode(Alarm responseMessage) throws EncodeException {
            return JSONArray.toJSONString(responseMessage);
    }
}
