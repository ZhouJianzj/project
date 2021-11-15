package com.zj.service;

import com.alibaba.druid.sql.visitor.functions.Concat;
import com.zj.dao.ModelDao;
import com.zj.entity.CommonResponse;
import com.zj.entity.Perm;
import com.zj.entity.PipeModel;
import com.zj.util.FileNameUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

/**
 * @author zhoujian
 */
@Service
public class ModelServiceImpl implements ModelService {
    @Resource
    private ModelDao modelDao;

    @Value("${rootPath}")
    private String rootPath;

    /**
     * 单文件上传，地址为 磁盘地址 + 日期 + uuid + 文件名字
     * @param file 单个文件
     * @return 返回操作结果
     */
    @Override
    public CommonResponse<Boolean> fileUploadService(MultipartFile file) {

        if (file.isEmpty()){
            return new CommonResponse<>(400,"上传文件失败！",false);
        }
        //本地文件地址
        File hostFile = new File(FileNameUtil.generateFileName(rootPath,file.getOriginalFilename()));
        if (!hostFile.exists()){
            hostFile.mkdirs();
        }
        //转换
        try {
            file.transferTo(hostFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new CommonResponse<>(200,"上传文件成功！",true);
    }

    /**
     * 多文件上传,实现就是使用for循环一个一个保存
     * @param pipeModel 管道模型对象
     * */
    @Override
    public CommonResponse<Boolean> filesUploadService(PipeModel pipeModel){


        MultipartFile[] files = pipeModel.getFiles();
        //没有对应的文件就不能新增
        if (files.length == 0){
            return new CommonResponse<Boolean>(400,"文件上传失败！",false);
        }
        int count = 1;
        for(MultipartFile file : files){
            File hostFile = new File(FileNameUtil.generateFileName(rootPath,file.getOriginalFilename()));
            //记录
            count ++;
            if (!hostFile.exists()){
                hostFile.mkdirs();
            }
            try {
                file.transferTo(hostFile);
                if (count == 1 ){
                      pipeModel.setPipeIntroduce(hostFile.getAbsolutePath());
                }else if (count == 2){
                      pipeModel.setPipePic(hostFile.getAbsolutePath());
                }else if (count == 3){
                      pipeModel.setPipeManual(hostFile.getAbsolutePath());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (modelDao.pipeModelInsert(pipeModel)){
            return new CommonResponse<Boolean>(200,"添加模型成功！",true);
        }else {
            return new CommonResponse<Boolean>(400,"添加模型失败！",false);
        }
    }

}
