package com.zj.service;

import com.zj.entity.CommonResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zhoujian
 */

public interface ModelService {
    CommonResponse<String> fileUploadService(MultipartFile file);
}
