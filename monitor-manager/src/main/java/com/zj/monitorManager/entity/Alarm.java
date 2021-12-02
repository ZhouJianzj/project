package com.zj.monitorManager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author HuXinJie
 * @version 1.0
 * @date 2021/11/23 9:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alarm implements Serializable {

    private int sensorId;

    private String currentValue;

    private String alarmMsg;

    private Boolean isHandled;

    private Date alarmTime;

}
