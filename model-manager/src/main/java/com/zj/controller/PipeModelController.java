package com.zj.controller;

import com.zj.annotation.IgnoreResponseAdvice;
import com.zj.entity.CommonResponse;
import com.zj.entity.Page;
import com.zj.entity.PipeModel;
import com.zj.service.ModelService;
import com.zj.util.MyPageHelper;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
public class PipeModelController {
    @Resource
    private ModelService modelService;

    /**
     * 单文件上传
     */
    @PostMapping("upload")
    public CommonResponse<Boolean> fileUploadController(@RequestParam("file") MultipartFile file, HttpServletRequest req){
        return modelService.fileUploadService(file,req);
    }

    /**
     * 多文件上传 ---管道新增
     * 必须满足上传三个文件，而且文件内容不允许为空
     */
    @PostMapping("pipeModel")
    public CommonResponse filesUploadController(@RequestBody PipeModel pipeModel){
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

    @PutMapping("pipeModelSuper")
    public Boolean pipeModelModifyControllerT(@RequestBody PipeModel pipeModel){
        return modelService.pipeModelModifyService(pipeModel);
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

    /**
     * 支持多字段查询和分页
     * @param key 多字段
     * @param pageNo 页码
     * @param pageSize 数据量
     * @return 返回的结果集
     */
    @GetMapping("pipeModel")
    public Page<PipeModel> findPipeModelsController(@RequestParam(value = "key") String key,
                                                    @RequestParam(value = "pageNo",defaultValue = "1") String pageNo,
                                                    @RequestParam(value = "pageSize",defaultValue = "8") String pageSize){

       return   MyPageHelper.myPageHelper(new Page(modelService.findPipeModelsService(key),Integer.parseInt(pageNo),Integer.parseInt(pageSize)));
    }



    @PostMapping("test")
    public String uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest req) {
        String tmp= RandomStringUtils.randomAlphanumeric(9);

        String realPath = req.getServletContext().getRealPath("/upload");
        System.out.println("realPath:" + realPath);

        File folder = new File(realPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        try {
            file.transferTo(new File(folder,tmp));
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

        String url = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/upload/" +tmp;
        System.out.println(url);
        return tmp;
    }
}
