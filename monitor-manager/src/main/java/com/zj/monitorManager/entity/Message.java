package com.zj.monitorManager.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhoujian
 */
@Data
public class Message  implements Serializable {
    String name;
    String address;
}
