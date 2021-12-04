package com.zj.monitorManager.web;


import com.zj.monitorManager.entity.Item;
import com.zj.monitorManager.entity.Sensor;
import com.zj.monitorManager.service.AlarmService;
import com.zj.monitorManager.utils.ListMapUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@RestController
public class Test {
    @Resource
    AlarmService alarmService;

    /**
     * 先查询出所有的item对应的管道，
     * item
     *    pipe
     *      pipeModel
     *      sensor
     *          sensorModel
     *          alarm
     *
     */
    @GetMapping("testList")
    public List<Item> testList(){
       return alarmService.selectIPSMAAService();
    }

    @GetMapping("testGetSensorMap")
    public HashMap testGetSensorMap(){
        List<Item> items = alarmService.selectIPSMAAService();
        HashMap<String, HashMap<String, HashMap<String, Object>>> listToMap = ListMapUtil.listToMap(items);
        //获取所有的item的key
        Set<String> listToMapKeySet = listToMap.keySet();
        for (String listToMapKey:listToMapKeySet ){
            HashMap<String, HashMap<String, Object>> ItemHashMap = listToMap.get(listToMapKey);
            //获取所有pipe的key
            Set<String> pipeKeySets = ItemHashMap.keySet();
            for (String pipeKeySet :pipeKeySets){
                HashMap<String, Object> PipeHashMap = ItemHashMap.get(pipeKeySet);
                //包含sensorId的key
                Set<String> fieldKeySets = PipeHashMap.keySet();
                for (String fieldKeySet:fieldKeySets){
                    System.out.println(fieldKeySet);
                }

            }
        }

        return null;
    }

    @GetMapping("testMap")
    public HashMap testMap(){
        List<Item> items = alarmService.selectIPSMAAService();
        System.out.println(items);
        return ListMapUtil.listToMap(items);
    }

    @GetMapping("testSensorAll")
    public List<Sensor> testSensorAll(){
        return alarmService.selectSensorAll();
    }

    /**
     * 测试传感器转换成
     * @return
     */
    @GetMapping("testSensorToMap")
    public HashMap testSensorToMap(){
        List<Sensor> sensors = alarmService.selectSensorAll();
        return ListMapUtil.sensorToMap(sensors.get(0));
    }

    /**
     * 测试获取共享hashMap
     * @return
     */
    @GetMapping("testGetShareHashMap")
    public HashMap testGetShareHashMap(){
        return ListMapUtil.hashMapA;
    }

    /**
     * 模拟生成一个传感器的报警数据，获取共享hashMap查看有咩有变化
     * @param sensor
     * @return
     */
    @PostMapping("postSensorToHashMap")
    public HashMap postSensorToHashMap(@RequestBody  Sensor sensor){
        System.out.println("前端给的sensor报警数据" +  sensor.toString());
        ListMapUtil.forShareHashMap(ListMapUtil.hashMapA,sensor);
        return ListMapUtil.hashMapA;
    }

}
