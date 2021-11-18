package sensorModel.dao;

import com.zj.entity.SensorModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SensorModelDao {

    List<SensorModel> sensorModelSelect(String key);

    boolean sensorModelDelete(int id);

    boolean sensorModelInsert(SensorModel sensorModel);

    boolean sensorModelUpdate(SensorModel sensorModel);

    boolean test(String test);
}
