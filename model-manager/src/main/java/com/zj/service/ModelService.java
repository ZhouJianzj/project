package com.zj.service;

import com.zj.entity.CommonResponse;
import com.zj.entity.PipeModel;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zhoujian
 */

public interface ModelService {
    CommonResponse<Boolean> fileUploadService(MultipartFile file);

    CommonResponse<Boolean> filesUploadService(PipeModel pipeModel);
}
