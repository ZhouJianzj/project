package com.zj.controller;


import com.zj.entity.CommonResponse;
import com.zj.entity.Page;
import com.zj.entity.Pipe;
import com.zj.entity.Sensor;
import com.zj.service.AssetService;
import com.zj.util.MyPageHelper;
import org.springframework.web.bind.annotation.*;
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
     * 添加单个管道
     * @param pipe
     * */
    @PostMapping("pipe")
    public CommonResponse<Boolean> insertPipeController(@RequestBody Pipe pipe){
        return assetService.insertPipeService(pipe);
    }

    /**
     * 删除单个管道
     * @param id
     * */
    @DeleteMapping("pipe")
    public CommonResponse<Boolean> deletePipeController(@RequestParam("id") int id){
        return assetService.deletePipeService(id);
    }

    /**
     * 修改单个管道
     * @param pipe
     * */
    @PutMapping("pipe")
    public CommonResponse<Boolean> modifyPipeController(@RequestBody Pipe pipe){
        return assetService.modifyPipeService(pipe);
    }

    /**
     * 查询管道
     * @param key
     * @param pageNo
     * @param pageSize
     * */
    @GetMapping("pipe")
    public Page selectPipeController( @RequestParam(value = "pageNo", defaultValue = "1") String pageNo,
                                      @RequestParam(value = "pageSize", defaultValue = "8") String pageSize,
                                      @RequestParam("key") String key){
        return MyPageHelper.myPageHelper(new Page(assetService.selectPipeService(key),Integer.parseInt(pageNo),Integer.parseInt(pageSize)));
    }

    /**
     * 根据项目id查询管道
     * */
    @GetMapping("pipe/itemId")
    public List<Pipe> selectPipeByItemIdController(String itemId){
        return assetService.selectPipeByItemIdService(itemId);
    }

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
     *查询传感器
     * @param key 查询关键字
     * @param pageNo 页码
     * @param pageSize 一页数据量
     * @return 返回的结果集
     */
    @GetMapping("sensor")
    public Page findSensorController(@RequestParam("key") String key
            ,@RequestParam(value = "pageNo",defaultValue = "1") String pageNo,
             @RequestParam(value = "pageSize",defaultValue = "8") String pageSize){
        return MyPageHelper.myPageHelper(new Page(assetService.findSensorService(key),Integer.parseInt(pageNo),Integer.parseInt(pageSize)));

    }
    /**
     * 查询未被使用的sensor
     * */
    @GetMapping("sensorId")
    public List<Sensor> findSensorIdController(){
        return assetService.findSensorIdService();
    }

    /**
     * 修改传感器
     * @param sensor 接受的修改传感器的参数对象
     * @return 返回
     */
    @PutMapping("sensor")
    public Boolean modifySensorController(@RequestBody Sensor sensor){
        return assetService.modifySensorService(sensor);
    }
}
