package com.zj.entity;

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
    Date manuDate;
    Date productDate;
    PipeModel pipeModel;
    Sensor sensor;
    Item item;
    Organize organize;
}
