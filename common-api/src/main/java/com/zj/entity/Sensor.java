package com.zj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author HuXinJie
 * @version 1.0
 * @date 2021/11/18 8:17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sensor implements Serializable {
    int id;
    String sensorName;
    String sensorCode;
    String protocal;
    SensorModel sensorModel;
    Item item;
    Organize organize;
}
