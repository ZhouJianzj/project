package com.zj.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zj.entity.CommonResponse;
import com.zj.entity.Page;
import com.zj.entity.SensorModel;
import com.zj.service.SensorModelService;
import com.zj.util.MyPageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("model")
public class SensorModelController {
    @Autowired
    private SensorModelService sensorModelService;
    @GetMapping("test")
    public boolean test(){
         return sensorModelService.test();
    }
    /**
     * 查询传感器模型
     * @param key
     * @param pageNo
     * @param pageSize
     * @return item
     * */
    @GetMapping("sensorModel")
    public Page<SensorModel> findSensorModelController(@RequestParam(value ="pageNo",defaultValue = "1") String pageNo,
                                          @RequestParam(value = "pageSize",defaultValue = "8") String pageSize,
                                          @RequestParam(value = "key") String key){

        //PageHelper.startPage(Integer.parseInt(pageNo),Integer.parseInt(pageSize));
        return MyPageHelper.myPageHelper(new Page(sensorModelService.findSensorModelListService(key),Integer.parseInt(pageNo),Integer.parseInt(pageSize)));
                //new PageInfo<SensorModel>(sensorModelService.findSensorModelListService(key));
    }
    /**
     * 删除传感器模型
     * @param id
     * @return boolean
     * */
    @DeleteMapping("sensorModel")
    public CommonResponse<Boolean> deleteSensorModelByIdController(int id) {
        return sensorModelService.deleteSensorModelService(id);
    }
    /**
     * 添加传感器模型
     * @param sensorModel
     * @return commonResponse
     */
    @PostMapping("sensorModel")
    public CommonResponse<SensorModel> addSensorModelController(@RequestBody SensorModel sensorModel){
        return sensorModelService.addSensorModelService(sensorModel);
    }

    /**
     * 修改传感器模型
     * @param sensorModel
     * @return commonResponse
     */
    @PutMapping("sensorModel")
    public CommonResponse<SensorModel> modfiySensorModelController(@RequestBody SensorModel sensorModel){

        return sensorModelService.modfiySensorModelService(sensorModel);
    }
}
