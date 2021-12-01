package com.zj.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * (TSensorModel)实体类
 *
 * @author makejava
 * @since 2021-11-18 10:10:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SensorModel implements Serializable {

    private Integer id;

    private String deviceName;

    private String deviceType;

    private String deviceNumber;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private Integer upInterval;

    private String protocol;

    private String dataPointName;

    private Integer lowThreshold;

    private Integer highThreshold;

    private String dataPointExtra;

}