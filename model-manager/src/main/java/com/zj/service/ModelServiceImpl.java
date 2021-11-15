package com.zj.service;

import com.alibaba.druid.sql.visitor.functions.Concat;
import com.zj.dao.ModelDao;
import com.zj.entity.CommonResponse;
import com.zj.entity.Perm;
import com.zj.entity.PipeModel;
import com.zj.util.FileNameUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
        if (files.length < 3){
            System.out.println(files.length);
            return new CommonResponse<Boolean>(400,"上传材料不足！",false);
        }
        int count = 0;
        for(MultipartFile file : files){
            File hostFile = new File(FileNameUtil.generateFileName(rootPath,file.getOriginalFilename()));
            //记录
            count ++;
            if (!hostFile.exists()){
                hostFile.mkdirs();
            }
            try {
                file.transferTo(hostFile);
                //依次设值
                if (count == 1 ){
                      pipeModel.setPipeIntroduce(hostFile.getAbsolutePath());
                    System.out.println(pipeModel.getPipeIntroduce());
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

    @Override
    public Boolean test() {
        return modelDao.test("test");
    }

    /**
     * 满足文件下载的需要
     * @param id 管道模型的id
     * @param request 请求体
     * @return 返回值是一个responseEntity
     * @throws Exception 异常处理
     */
    @Override
    public  ResponseEntity<byte[]> findPipeModelService(String id ,
                                                        HttpServletRequest request)throws Exception {
        //优化，为了解决同一个用户查询三次的不必要的数据库操作
        String i = "";
        PipeModel pipeModel = null;
        if (!i.equals(id)){
            pipeModel =  modelDao.findPipeModelDao(id);
            i = pipeModel.getId();
            System.out.println(pipeModel.toString() + "============");
        }

        //截取文件名字
        String absPath =  pipeModel.getPipeIntroduce();
        int lastIndexOf = absPath.lastIndexOf("\\");
        int lastIndexOf1 = absPath.lastIndexOf(".");
        String filename = absPath.substring(lastIndexOf + 1, lastIndexOf1);
        System.out.println( "需要下载的文件名字：" + filename);
        //下载文件路径,正则转\需要两次转译
        File file = new File(absPath.replaceAll("\\\\","/"));
        System.out.println("所需要下载文件的绝对路径：" + absPath.replaceAll("\\\\","/") );


        //开始设置http请求头
        HttpHeaders headers = new HttpHeaders();
        //下载显示的文件名，解决中文名称乱码问题
        String downloadFileName = new String(filename.getBytes("UTF-8"),"ISO-8859-1");
        //通知浏览器以attachment（下载方式）打开文件。
        headers.setContentDispositionFormData("attachment", downloadFileName);

        //设置mime：application/octet-stream ： 二进制流数据（最常见的文件下载）。
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(
                FileUtils.readFileToByteArray(file),
                //把一个文件转换成字节数组返回
                headers,
                //http请求头
                HttpStatus.OK
                //200
        );
    }

    /**
     * 文件删除 --- 修改管道模型
     * @pipeModel  修改之后的挂到模型对象
     * @return 返回修改是否成功
     */
    @Override
    public CommonResponse<Boolean> fileDeleteService(PipeModel pipeModel) {
        PipeModel pipeModelDao = modelDao.findPipeModelDao(pipeModel.getId());
        //获取到数据库中的真实存在的地址
        String[] pathsDao = {pipeModelDao.getPipeIntroduce(),pipeModelDao.getPipePic(),pipeModelDao.getPipeManual()};
        //获取修改之后的文件地址
        String[] paths = {pipeModel.getPipeIntroduce(),pipeModel.getPipePic(),pipeModel.getPipeManual()};
       //如果是传递来的三个文件属性是空的话就直接先删除文件后修改数据库
        for (int i = 0;i < paths.length ;i++){
            if ("".equals(paths[i]) || paths[i] == null){
                // 如果修改之后地址为null就直接删除文件 ，下载文件路径,正则转\需要两次转译
                File file = new File(pathsDao[i].replaceAll("\\\\","/"));
                if (file.exists()) {
                    file.delete();
                }
            }
        }

       if (modelDao.updatePipeModel(pipeModel)){
           return new CommonResponse<>(200,"修改成功！",true);
       }else {
           return new CommonResponse<>(400,"修改失败！",false);
       }
    }

}
