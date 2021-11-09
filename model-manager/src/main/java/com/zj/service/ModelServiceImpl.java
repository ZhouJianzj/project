package com.zj.service;

import com.zj.dao.ModelDao;
import com.zj.entity.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

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

        if (file.isEmpty()){
            return new CommonResponse<>(400,"上传文件失败！");
        }
        //本地文件地址
        File hostFile = new File(rootPath + file.getOriginalFilename());
        if (!hostFile.exists()){
            hostFile.mkdirs();
        }
        //转换
        try {
            file.transferTo(hostFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new CommonResponse<>(200,"上传文件成功！");
    }
}
