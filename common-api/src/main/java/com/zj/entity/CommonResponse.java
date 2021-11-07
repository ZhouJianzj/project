package com.zj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author zhoujian
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class CommonResponse<T>   implements Serializable   {

    private Integer status;
    private String msg;

    private T data;

    public CommonResponse(Integer status ,String msg){
        this.status = status;
        this.msg = msg;
    }
}
