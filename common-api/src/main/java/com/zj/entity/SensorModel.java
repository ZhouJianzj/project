package com.zj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author HuXinJie
 * @version 1.0
 * @date 2021/11/18 8:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SensorModel implements Serializable {
    int id;
    String deviceName;
    String deviceType;
    String deviceNumber;
    Date createTime;
    int upInterval;
    String protocol;
    String dataPointName;
    int lowThreshold;
    int highThreshold;
    String dataPointExtra;
}
