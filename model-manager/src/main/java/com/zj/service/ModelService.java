package com.zj.service;

import com.zj.entity.CommonResponse;
import com.zj.entity.PipeModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhoujian
 */

public interface ModelService {
    CommonResponse<Boolean> fileUploadService(MultipartFile file);

    CommonResponse<Boolean> filesUploadService(PipeModel pipeModel);


    ResponseEntity<byte[]> findPipeModelService(String id,String num, HttpServletRequest request) throws Exception;

    CommonResponse<Boolean> fileDeleteService(PipeModel pipeModel);

    Boolean pipeModelDelete(String id);
}
