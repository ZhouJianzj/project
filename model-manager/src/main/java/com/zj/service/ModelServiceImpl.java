package com.zj.service;

import com.zj.dao.ModelDao;
import com.zj.entity.CommonResponse;
import com.zj.entity.PipeModel;
import com.zj.util.FileUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

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
        File hostFile = new File(FileUtil.generateFileName(rootPath,file.getOriginalFilename()));
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
     * 多文件上传,实现管道添加
     * 就是使用for循环一个一个保存
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
            File hostFile = new File(FileUtil.generateFileName(rootPath,file.getOriginalFilename()));
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


    /**
     * 满足查看指定文件功能需要
     * 指定单文件文件下载
     * @param id 管道模型的id
     * @param num  1:introduce 2:pic 3:Manual
     * @param request 请求体
     * @return 返回值是一个responseEntity
     * @throws Exception 异常处理
     */
    @Override
    public  ResponseEntity<byte[]> findPipeModelService(String id ,String num,
                                                        HttpServletRequest request)throws Exception {
        //优化，为了解决同一个用户查询三次的不必要的数据库操作
        String i = "";
        PipeModel pipeModel = null;
        if (!i.equals(id)){
            pipeModel =  modelDao.findPipeModelDao(id);
            i = pipeModel.getId();
            System.out.println(pipeModel.toString() + "============");
        }
        //指定下载哪一个文件
        String absPath = null ;
        if ("1".equals(num) ){
            absPath = pipeModel.getPipeIntroduce();
        }
        if ("2".equals(num)){
            absPath = pipeModel.getPipePic();
        }
        if ("3".equals(num)){
            absPath = pipeModel.getPipeManual();
        }
        if (null == num){
            return null;
        }
        int lastIndexOf = absPath.lastIndexOf("\\");
        int lastIndexOf1 = absPath.lastIndexOf(".");
        String filename = absPath.substring(lastIndexOf + 1, lastIndexOf1);
        //下载文件路径,正则转\需要两次转译
        File file = new File(absPath.replaceAll("\\\\","/"));

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
     * 修改管道模型
     * @return 返回修改是否成功
     */
    @Override
    public CommonResponse<Boolean> fileDeleteService(PipeModel pipeModel) {
        //获取数据库中的真实数据，给三个文件属性地址装载在一个数组中
        PipeModel pipeModelDao = modelDao.findPipeModelDao(pipeModel.getId());
        String[] pathsDao = {pipeModelDao.getPipeIntroduce(),pipeModelDao.getPipePic(),pipeModelDao.getPipeManual()};
        pipeModel.setPipeIntroduce(null);
        pipeModel.setPipePic(null);
        pipeModel.setPipeManual(null);
        //前端修改之后的三个文件属性文件
        MultipartFile[] files = pipeModel.getFiles();
        int count = 0;
        for (int i = 0;i < files.length;i++){
            //为null的时候表示不修改，反之则为修改了，我们这里是先删除文件后修改数据库
            if (files[i] != null){
                String dirFile = pathsDao[i].replaceAll("\\\\", "/");
                String dir = dirFile.substring(0, dirFile.lastIndexOf("/"));
                //删除文件
                File file = new File(dirFile);
                if (file.exists()){
                    file.delete();
                }
                //删除目录，日期一下的目录
                File file1 = new File(dir);
                if (file1.list().length == 0){
                    file1.delete();
                }
                //存放文件
                File hostFile = new File(FileUtil.generateFileName(rootPath,files[i].getOriginalFilename()));
                if (!hostFile.exists()){
                    hostFile.mkdirs();
                }
                //计数为了给指定的文件属性赋值
                count++;
                try {
                    files[i].transferTo(hostFile);
                    if (count == 1){
                        pipeModel.setPipeIntroduce(hostFile.getAbsolutePath());
                    }
                    if (count == 2){
                        pipeModel.setPipePic(hostFile.getAbsolutePath());
                    }
                    if (count == 3){
                        pipeModel.setPipeManual(hostFile.getAbsolutePath());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //传递来的三个表示文件的属性为空的话表示不修改之前上传的文件只修改字段
       if (modelDao.updatePipeModel(pipeModel)){
           return new CommonResponse<>(200,"修改成功！",true);
       }else {
           return new CommonResponse<>(400,"修改失败！",false);
       }
    }

    /**
     * 删除指定id的管道模型，先删除文件后删除数据库
     * @param id 管道模型的id
     * @return 返回删除是否成功
     */
    @Override
    @Transactional
    public Boolean pipeModelDelete(String id) {
        //查询指定id的管道模型
        PipeModel pipeModelDao = modelDao.findPipeModelDao(id);
        //获取到数据库中的真实存在的地址
        String[] pathsDao = {pipeModelDao.getPipeIntroduce(),pipeModelDao.getPipePic(),pipeModelDao.getPipeManual()};
        //如果是传递来的三个文件属性是空的话就直接先删除文件后修改数据库
        for (int i = 0;i < pathsDao.length ;i++){
                // 下载文件路径,正则转\需要两次转译
                String dirFile = pathsDao[i].replaceAll("\\\\", "/");
                String dir = dirFile.substring(0, dirFile.lastIndexOf("/"));
                //删除文件
                File file = new File(dirFile);
                if (file.exists()){
                    file.delete();
                }
                //删除目录，日期下的目录
                File file1 = new File(dir);
                if (file1.list().length == 0){
                    file1.delete();
                }
        }
        //删除对管道对应的管道模型id
        if (modelDao.pipeUpdateModelId(id) && modelDao.pipeModelDelete(id)){
            return true;
        }else {
            return false;
        }

    }

    /**
     * 多字段查询支持模糊查询
     * @param key 关键字
     * @return 返回的结果集
     */
    @Override
    public List<PipeModel> findPipeModelsService(String key) {
        return modelDao.PipeModelsSelect(key);
    }

}
