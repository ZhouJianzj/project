package com.zj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author HuXinJie
 * @version 1.0
 * @date 2021/11/24 8:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlarmMon implements Serializable {

    int id;

    String alarmMsg;

    String currentValue;

    Boolean handled;

    Boolean normal;

    Sensor sensor;
}
