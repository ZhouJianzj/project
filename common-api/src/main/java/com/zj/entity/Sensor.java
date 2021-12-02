package com.zj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 传感器
 * @author zhoujian
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sensor implements Serializable {
    private String id;
    private String sensorName;
    private String sensorCode;
    private String protocal;
    private String sensorModelId;
    private String itemId;
    private String orgaId;

    private SensorModel sensorModel;
    private Item item;
    private Organize organize;

}
