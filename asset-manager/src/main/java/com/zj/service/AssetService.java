package com.zj.service;

import com.zj.entity.Sensor;

import java.util.List;

/**
 * @author zhoujian
 */

public interface AssetService {

    Boolean addSensorService(Sensor sensor);

    Boolean deleteSensorService(String id);

    List<Sensor> findSensorService(String key);
}
