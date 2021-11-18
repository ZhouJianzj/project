package sensorModel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zj.entity.CommonResponse;
import com.zj.entity.SensorModel;
import sensorModel.dao.SensorModelDao;

import java.util.List;

@Service
public class SensorModelServiceImpl implements SensorModelService {
    @Autowired
    SensorModelDao sensorModelDao;
    /**
     * 查询
     * @param key
     * @return
     */
    @Override
    public List<SensorModel> findSensorModelListService(String key) {

        return sensorModelDao.sensorModelSelect(key);
    }
//
    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public CommonResponse<Boolean> deleteSensorModelService(int id) {
        CommonResponse<Boolean> commonResponse = new CommonResponse<>();
        if(sensorModelDao.sensorModelDelete(id)){
            commonResponse.setMsg("删除成功");
            commonResponse.setStatus(200);
        }else {
            commonResponse.setMsg("删除失败");
            commonResponse.setStatus(400);
        }
        return commonResponse;
    }
//
    /**
     * 添加
     * @param sensorModel
     * @return
     */
    @Override
    public CommonResponse<SensorModel> addSensorModelService(SensorModel sensorModel) {
        CommonResponse<SensorModel> commonResponse = new CommonResponse<>();
        if(sensorModelDao.sensorModelInsert(sensorModel)){
            commonResponse.setMsg("添加成功");
            commonResponse.setStatus(200);
        }else{
            commonResponse.setMsg("添加失败");
            commonResponse.setStatus(400);
        }
        return commonResponse;
    }
//
    /**
     * 修改
     * @param sensorModel
     * @return
     */
    @Override
    public CommonResponse<SensorModel> modfiySensorModelService(SensorModel sensorModel) {
        CommonResponse<SensorModel> commonResponse = new CommonResponse<>();
        if(sensorModelDao.sensorModelUpdate(sensorModel)){
            commonResponse.setMsg("修改成功");
            commonResponse.setStatus(200);
        }else{
            commonResponse.setMsg("修改失败");
            commonResponse.setStatus(400);
        }
        return commonResponse;
    }

    @Override
    public boolean test() {
        return sensorModelDao.test("test");
    }
}
