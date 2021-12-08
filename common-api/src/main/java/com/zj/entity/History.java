package com.zj.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author HuXinJie
 * @version 1.0
 * @date 2021/12/8 11:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class History implements Serializable {

    int id;

    Sensor sensor;

    String currentValue;

    String alarmMsg;

    Boolean isHandled;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    Date alarmTime;
}
