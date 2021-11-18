package com.zj.service;

import com.zj.dao.AssetDao;
import com.zj.entity.CommonResponse;
import com.zj.entity.Pipe;
import com.zj.entity.Sensor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author HuXinJie
 * @version 1.0
 * @date 2021/11/18 8:55
 * @author zhoujian
 */
@Service
public class AssetServiceImpl implements AssetService {
    @Resource
    private AssetDao assetDao;

    @Override
    public CommonResponse<Boolean> insertPipeService(Pipe pipe) {
        CommonResponse<Boolean> response = new CommonResponse<>();
        if (assetDao.pipeInsert(pipe)){
            response.setMsg("新增成功");
            response.setStatus(200);
        }else {
            response.setMsg("新增失败");
            response.setStatus(400);
        }
        return response;
    }

    @Override
    public CommonResponse<Boolean> deletePipeService(int id) {
        CommonResponse<Boolean> response = new CommonResponse<>();
        if (assetDao.pipeDelete(id)){
            response.setMsg("删除成功");
            response.setStatus(200);
        }else {
            response.setMsg("删除失败");
            response.setStatus(400);
        }
        return response;
    }

    @Override
    public CommonResponse<Boolean> modifyPipeService(Pipe pipe) {
        CommonResponse<Boolean> response = new CommonResponse<>();
        if (assetDao.pipeModify(pipe)){
            response.setStatus(200);
            response.setMsg("修改成功");
        }else {
            response.setStatus(400);
            response.setMsg("修改失败");
        }
        return response;
    }

    @Override
    public List<Pipe> selectPipeService(String key) {
        return assetDao.pipeSelect(key);
    }
    /**
     * 传感器的新增
     * @param sensor  新的传感器对象
     * @return 操作是否成功
     */
    @Override
    public Boolean addSensorService(Sensor sensor) {
        if (sensor != null){
            return assetDao.insertSensorDao(sensor);
        }else {
            return false;
        }
    }

    /**
     *  根据id删除
     * @param id 传感器的id
     * @return 操作是否成功
     */
    @Override
    public Boolean deleteSensorService(String id) {
        return assetDao.deleteSensorDao(id);
    }

    /**
     * 根据关键字查询传感器
     * @param key 关键字段 包括 id name code protocal
     * @return 结果集
     */
    @Override
    public List<Sensor> findSensorService(String key) {

        return assetDao.selectSensorDao(key);
    }

}