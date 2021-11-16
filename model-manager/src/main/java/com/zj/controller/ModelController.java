package com.zj.controller;

import com.zj.annotation.IgnoreResponseAdvice;
import com.zj.entity.CommonResponse;
import com.zj.entity.PipeModel;
import com.zj.service.ModelService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;
import java.io.File;

/**
 * @author zhoujian
 * 方法名命名规范
 * -------------------------------------------------------------------------------
 * url规范：
 * 例如：
 *      管道管理的所用url根都是
 *      /pipe
 *      如果根据管道id查询管道
 *      /pipe/id
 * -------------------------------------------------------------------------------
 * controller层类中方法命名规范
 * 使用规范的请求：
 *      PostMapping(新增) DeleteMapping(删除) GetMapping(查询) PutMapping(修改)
 * 动词：
 *      查询 find
 *      添加 add
 *      删除 delete
 *      修改 modify
 * 例如：
 * 添加管道的控制器方法           add  +  pipe  + controller = addPipeController
 * 根据id查询管道的控制器方法      find + pipe  + id + controller = findPipeIdController
 * 删除指定id的管道控制器方法      delete + pipe  + id + controller = deletePipeIdController
 * 修改指定id的官管道的控制器方法   modfiy + pipe  + id + controller = modfiyPipeIdController
 * ------------------------------------------------------------------------------
 * service层类中方法名规范： 动词与控制器中的相同
 * 例如：
 *      addPipeController ------>  addPipeService
 * ------------------------------------------------------------------------------
 * dao层类方法命名规范：
 * 动词：
 *      查询：select
 *      添加：insert
 *      删除：delete
 *      修改：update
 *
 *例如：
 *      添加管道：
 *      addPiepService --------->pipeInsert
 *      pipe + insert = pipeInsert
 *
 *      根据id查询管道：
 *      pipeIdSelect
 *
 */
@RestController
@RequestMapping("model")
public class ModelController {
    @Resource
    private ModelService modelService;

    /**
     * 单文件上传
     */
    @PostMapping("upload")
    public CommonResponse<Boolean> fileUploadController(@RequestParam("file") MultipartFile file){
        return modelService.fileUploadService(file);
    }

    /**
     * 多文件上传 ---管道新增
     * 必须满足上传三个文件，而且文件内容不允许为空
     */
    @PostMapping("pipeModel")
    public CommonResponse filesUploadController( PipeModel pipeModel){
        return modelService.filesUploadService(pipeModel);
    }


    /**
     * 单文件下载----满足查询所有管道模型时候查看三个说明书的
     * @param id 模型id
     * @param num  1:introduce 2:pic 3:Manual
     * @param request 请求体
     * @return 返回字节文件
     */
        @GetMapping("download")
        @IgnoreResponseAdvice
        public ResponseEntity<byte[]> fileDownload(String id,String num, HttpServletRequest request) {
            try {
                return modelService.findPipeModelService(id,num,request);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

    /**
     *  管道修改
     * @param pipeModel 前端传递来的修改对象
     * @return 返回修改结果集
     */
    @PutMapping("pipeModel")
    public CommonResponse<Boolean> pipeModelModifyController(PipeModel pipeModel){
        return  modelService.fileDeleteService(pipeModel);
    }


    /**
     * 管道模型的删除
     * @param id 指定删除管道模型的id
     * @return 操作时候成功
     */
    @DeleteMapping("pipeModel")
    public Boolean pipeModelDelete(String id){
        return  modelService.pipeModelDelete(id);
    }
}
