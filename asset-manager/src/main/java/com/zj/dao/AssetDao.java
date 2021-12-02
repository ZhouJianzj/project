package com.zj.dao;

import com.zj.entity.Pipe;
import com.zj.entity.Sensor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * @author zhoujian
 */
@Repository
public interface AssetDao {

    boolean pipeInsert(Pipe pipe);

    boolean pipeDelete(int id);

    boolean pipeModify(Pipe pipe);

    List<Pipe> pipeSelect(String key);
    Boolean insertSensorDao(Sensor sensor);

    Boolean deleteSensorDao(String id);

    List<Sensor> selectSensorDao(String key);


    Boolean modifySensorDao(Sensor sensor);

    List<Pipe> selectPipeBySensor(int id ,String sensorId);

    List<Sensor> selectSensor(String id);

    List<Sensor> SensorIdSelect();

    List<Pipe> PipeByItemIdSelect(String itemId);
}
