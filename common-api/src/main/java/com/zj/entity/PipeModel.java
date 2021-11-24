package com.zj.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * @author zhoujian
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PipeModel {
    private String id;

    private String pipeName;

    private String pipeNumber;

    private String pipeType;

    private String pipeIntroduce;

    private String pipePic;

    private String pipeManual;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private MultipartFile[] files;
}
