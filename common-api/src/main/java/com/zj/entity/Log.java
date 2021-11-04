package com.zj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * @author zhoujian
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Log {

    private Integer id;
    private String username;
    private String operType;
    private String moduleName;
    private boolean result;
    private Date operTimer;
    private String operContent;


}
