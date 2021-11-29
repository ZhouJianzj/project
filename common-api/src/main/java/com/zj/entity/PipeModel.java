package com.zj.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author zhoujian
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PipeModel implements Serializable {
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

    /**
     * 文件名字
     */
    private ArrayList<String> fileName = new ArrayList<>(3);

    /**
     *相对地址
     */
    private ArrayList<String> fileRelativePath =  new ArrayList<>(3);
}
