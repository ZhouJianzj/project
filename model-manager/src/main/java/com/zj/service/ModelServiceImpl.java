package com.zj.service;

import com.zj.dao.ModelDao;
import com.zj.entity.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author zhoujian
 */
@Service
public class ModelServiceImpl implements ModelService {
    @Resource
    private ModelDao modelDao;

    @Value("${rootPath}")
    private String rootPath;

    @Override
    public CommonResponse<String> fileUploadService(MultipartFile file) {

        return null;
    }
}
