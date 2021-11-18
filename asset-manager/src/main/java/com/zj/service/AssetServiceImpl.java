package com.zj.service;

import com.zj.dao.AssetDao;
import com.zj.entity.Sensor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhoujian
 */
@Service
public class AssetServiceImpl implements AssetService {
    @Resource
    private AssetDao assetDao;

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
