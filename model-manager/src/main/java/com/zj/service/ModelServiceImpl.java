package com.zj.service;

import com.alibaba.druid.sql.visitor.functions.Concat;
import com.zj.dao.ModelDao;
import com.zj.entity.CommonResponse;
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
     * @param files 多个文件形成的数据
     * */
    @Override
    public CommonResponse<Boolean> filesUploadService(MultipartFile[] files) {
        if (files.length == 0){
            return new CommonResponse<Boolean>(400,"文件上传失败！",false);
        }
        String pipeIntroduce = "";
        String pipePic = "";
        String pipeManual = "";
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
                    pipeIntroduce = hostFile.getAbsolutePath();
                }else if (count == 2){
                    pipePic = hostFile.getAbsolutePath();;
                }else if (count == 3){
                    pipeManual = hostFile.getAbsolutePath();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        if(!"".equals(pipeIntroduce) && !"".equals(pipePic) && !"".equals(pipeManual)){
//            modelDao.pipeModelFilesInsert(pipeIntroduce,pipePic,pipeManual);
//        }
        return new CommonResponse<Boolean>(200,"文件上传成功！",true);
    }

}
