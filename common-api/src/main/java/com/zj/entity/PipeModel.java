package com.zj.entity;

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
    private Integer id;

    private String pipeName;

    private String pipeNumber;

    private String pipeType;

    private String pipeIntroduce;

    private String pipePic;

    private String pipeManual;

    private Date createTime;

    private MultipartFile[] files;
}
