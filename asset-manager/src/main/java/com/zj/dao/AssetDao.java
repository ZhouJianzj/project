package com.zj.dao;

import com.zj.entity.Sensor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhoujian
 */
@Repository
public interface AssetDao {

    Boolean insertSensorDao(Sensor sensor);

    Boolean deleteSensorDao(String id);

    List<Sensor> selectSensorDao(String key);


}
