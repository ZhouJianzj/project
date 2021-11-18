package com.zj.service;

import com.zj.entity.CommonResponse;
import com.zj.entity.SensorModel;

import java.util.List;

public interface SensorModelService {

    List<SensorModel> findSensorModelListService(String key);

    CommonResponse<Boolean> deleteSensorModelService(int id);

    CommonResponse<SensorModel> addSensorModelService(SensorModel sensorModel);

    CommonResponse<SensorModel> modfiySensorModelService(SensorModel sensorModel);

    boolean test();
}
