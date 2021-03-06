package com.zj.service;

import com.zj.dao.AssetDao;
import com.zj.entity.CommonResponse;
import com.zj.entity.Pipe;
import org.springframework.stereotype.Service;
import com.zj.entity.Sensor;

import javax.annotation.Resource;
import java.util.List;
import java.util.List;

/**
 * @author zhoujian
 */
public interface AssetService {

    CommonResponse<Boolean> insertPipeService(Pipe pipe);

    CommonResponse<Boolean> deletePipeService(int id);

    CommonResponse<Boolean> modifyPipeService(Pipe pipe);

    List<Pipe> selectPipeService(String key);

    Boolean addSensorService(Sensor sensor);

    Boolean deleteSensorService(String id);

    List<Sensor> findSensorService(String key);

    Boolean modifySensorService(Sensor sensor);

    List<Sensor> findSensorIdService();

    List<Pipe> selectPipeByItemIdService(String itemId);
}
