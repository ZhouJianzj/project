package com.zj.controller;


import com.zj.entity.Sensor;
import com.zj.service.AssetService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhoujian
 */
@RestController
@RequestMapping("asset")
public class AssetController {
    @Resource
    private AssetService assetService;


    /**
     * 新增传感器
     * @param sensor 传感器对象
     * @return 操作结果
     */
    @PostMapping("sensor")
    public Boolean addSensorController(@RequestBody Sensor sensor) {
        return assetService.addSensorService(sensor);
    }

    /**
     *  根据id删除
     * @param id 传感器的id
     * @return 操作是否成功
     */
    @DeleteMapping("sensor")
    public Boolean deleteSensorController(@RequestParam(value = "id") String id){
        return assetService.deleteSensorService(id);

    }

    /**
     *
     * @param key 查询关键字
     * @param pageNo 页码
     * @param pageSize 一页数据量
     * @return 返回的结果集
     */
    @GetMapping("sensor")
    public List<Sensor> findSensorController(@RequestParam("key") String key
            ,@RequestParam(value = "pageNo",defaultValue = "1") String pageNo,
             @RequestParam(value = "pageSize",defaultValue = "8")                                String pageSize){
        return assetService.findSensorService(key);
    }

}
