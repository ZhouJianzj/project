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
 * @date 2021/11/18 8:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pipe implements Serializable {
    int id;
    String productName;
    String productCode;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    Date manuDate;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    Date productDate;
    PipeModel pipeModel;
    Sensor sensor;
    Item item;
    Organize organize;
}
