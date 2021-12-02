package com.zj.monitorManager.web;


import com.zj.monitorManager.entity.Item;
import com.zj.monitorManager.entity.Sensor;
import com.zj.monitorManager.service.AlarmService;
import com.zj.monitorManager.utils.ListMapUtil;
import org.springframework.web.bind.annotation.GetMapping;
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

        Set<String> listToMapKeySet = listToMap.keySet();
        for (String listToMapKey:listToMapKeySet ){
            HashMap<String, HashMap<String, Object>> ItemHashMap = listToMap.get(listToMapKey);
            Set<String> pipeKeySets = ItemHashMap.keySet();
            for (String pipeKeySet :pipeKeySets){
                HashMap<String, Object> fieldHashMaps = ItemHashMap.get(pipeKeySet);
                Set<String> fieldKeySets= fieldHashMaps.keySet();
                for (String fieldKeySet: fieldKeySets ){
                    System.out.println(ItemHashMap.get(fieldKeySet));
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

}
