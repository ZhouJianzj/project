package com.zj.monitorManager.utils;

import com.zj.monitorManager.entity.Item;
import com.zj.monitorManager.entity.Pipe;
import com.zj.monitorManager.entity.Sensor;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @author zhoujian
 */
public class ListMapUtil {

/**
 *  {
 *         "id": 1,
 *         "name": "未来登月",
 *         "pipes": [
 *             {
 *                 "id": 2,
 *                 "productName": "产品名5",
 *                 "productCode": "产品编码2",
 *                 "manuDate": "2022-09-01 12:13:53",
 *                 "productDate": "2021-10-04 12:13:53",
 *                 "pipeModel": {
 *                     "id": 1,
 *                     "pipeName": "管道名称1111",
 *                     "pipeNumber": "666",
 *                     "pipeType": "单波纹",
 *                     "pipeIntroduce": "C:/Users/zhoujian/AppData/Local/Temp/tomcat-docbase.4659173895447400607.8003/matter/lwjETJDf8/clipboard1.png",
 *                     "pipePic": "C:/Users/zhoujian/AppData/Local/Temp/tomcat-docbase.4659173895447400607.8003/matter/lwjETJDf8/clipboard1.png",
 *                     "pipeManual": "C:/Users/zhoujian/AppData/Local/Temp/tomcat-docbase.4659173895447400607.8003/matter/lwjETJDf8/clipboard1.png",
 *                     "createTime": "2021-11-26 11:56:42",
 *                     "fileName": [],
 *                     "fileRelativePath": []
 *                 },
 *                 "sensor": {
 *                     "id": 2,
 *                     "sensorName": "温度传感器",
 *                     "sensorCode": "test",
 *                     "protocal": "test",
 *                     "sensorModel": {
 *                         "id": 2,
 *                         "deviceName": "test11",
 *                         "deviceType": "温度传感器",
 *                         "deviceNumber": "999设备道",
 *                         "createTime": "2021-11-30T01:23:32.000+00:00",
 *                         "upInterval": 10,
 *                         "protocol": "LWM2M",
 *                         "dataPointName": "温度",
 *                         "lowThreshold": 0,
 *                         "highThreshold": 50,
 *                         "dataPointExtra": "腐蚀"
 *                     },
 *                     "alarm": {
 *                         "sensorId": 0,
 *                         "currentValue": "82",
 *                         "alarmMsg": "压力异常!当前压力:82",
 *                         "isHandled": false,
 *                         "alarmTime": "2021-12-02T00:05:03.000+00:00"
 *                     }
 *                 }
 *             },
 *             {
 *                 "id": 1,
 *                 "productName": "产品名4",
 *                 "productCode": "产品编码2",
 *                 "manuDate": "2022-09-01 12:13:53",
 *                 "productDate": "2021-10-04 12:13:53",
 *                 "pipeModel": {
 *                     "id": 1,
 *                     "pipeName": "管道名称1111",
 *                     "pipeNumber": "666",
 *                     "pipeType": "单波纹",
 *                     "pipeIntroduce": "C:/Users/zhoujian/AppData/Local/Temp/tomcat-docbase.4659173895447400607.8003/matter/lwjETJDf8/clipboard1.png",
 *                     "pipePic": "C:/Users/zhoujian/AppData/Local/Temp/tomcat-docbase.4659173895447400607.8003/matter/lwjETJDf8/clipboard1.png",
 *                     "pipeManual": "C:/Users/zhoujian/AppData/Local/Temp/tomcat-docbase.4659173895447400607.8003/matter/lwjETJDf8/clipboard1.png",
 *                     "createTime": "2021-11-26 11:56:42",
 *                     "fileName": [],
 *                     "fileRelativePath": []
 *                 },
 *                 "sensor": {
 *                     "id": 4,
 *                     "sensorName": "传感器名5",
 *                     "sensorCode": "传感器编码3",
 *                     "protocal": "传感器协议3",
 *                     "sensorModel": {
 *                         "id": 8,
 *                         "deviceName": "test2222222",
 *                         "deviceType": "位移传感器",
 *                         "deviceNumber": "测试编号01",
 *                         "createTime": "2021-12-01T13:39:25.000+00:00",
 *                         "upInterval": 22,
 *                         "protocol": "NB-IoT",
 *                         "dataPointName": "位移",
 *                         "lowThreshold": 10,
 *                         "highThreshold": 40,
 *                         "dataPointExtra": "位移"
 *                     },
 *                     "alarm": null
 *                 }
 *             }
 *         ]
 *     },
 */
    public static HashMap<String, HashMap<String,HashMap<String, Object>>> hashMapA = new HashMap<>();


    /**
     * List 转换成 Map
     * @param items item List
     * @return item Map
     *
     * {
     * 	"1": {
     * 		"1": {
     * 			"alarm": {
     * 				"alarmMsg": "压力异常！当前压力:53",
     * 				"alarmTime": 1638708961384,
     * 				"currentValue": "53",
     * 				"isHandled": false,
     * 				"sensorId": 1
     *                        },
     * 			"id": 1,
     * 			"protocal": "123",
     * 			"sensorCode": "1202",
     * 			"sensorModel": {
     * 				"createTime": 1638350641000,
     * 				"dataPointExtra": "泄露",
     * 				"dataPointName": "压力",
     * 				"deviceName": "1234设备道",
     * 				"deviceNumber": "1234",
     * 				"deviceType": "压力传感器",
     * 				"highThreshold": 20,
     * 				"id": 1,
     * 				"lowThreshold": 5,
     * 				"protocol": "MQTT",
     * 				"upInterval": 10
     *            },
     * 			"sensorName": "压力传感器"        * 		},
     * 		"productCode": "产品编码2",
     * 		"id": 1,
     * 		"manuDate": 1662005633000,
     * 		"pipeModel": {
     * 			"createTime": 1637899002000,
     * 			"fileName": ["clipboard1.png", "clipboard1.png", "clipboard1.png"],
     * 			"fileRelativePath": ["matter/lwjETJDf8/clipboard1.png", "matter/lwjETJDf8/clipboard1.png", "matter/lwjETJDf8/clipboard1.png"],
     * 			"id": 1,
     * 			"pipeIntroduce": "C:/Users/zhoujian/AppData/Local/Temp/tomcat-docbase.4659173895447400607.8003/matter/lwjETJDf8/clipboard1.png",
     * 			"pipeManual": "C:/Users/zhoujian/AppData/Local/Temp/tomcat-docbase.4659173895447400607.8003/matter/lwjETJDf8/clipboard1.png",
     * 			"pipeName": "管道名称1111",
     * 			"pipeNumber": "666",
     * 			"pipePic": "C:/Users/zhoujian/AppData/Local/Temp/tomcat-docbase.4659173895447400607.8003/matter/lwjETJDf8/clipboard1.png",
     * 			"pipeType": "单波纹"
     *        },
     * 		"productDate": 1633320833000,
     * 		"productName": "产品名4",
     * 		"sensorId": 1
     *    },
     * 	"2": {
     * 		"2": {
     * 			"alarm": {
     * 				"alarmMsg": "温度异常！当前温度:15",
     * 				"alarmTime": 1638708958384,
     * 				"currentValue": "15",
     * 				"isHandled": false,
     * 				"sensorId": 2
     *            },
     * 			"id": 2,
     * 			"protocal": "test",
     * 			"sensorCode": "test",
     * 			"sensorModel": {
     * 				"createTime": 1638235412000,
     * 				"dataPointExtra": "腐蚀",
     * 				"dataPointName": "温度",
     * 				"deviceName": "test11",
     * 				"deviceNumber": "999设备道",
     * 				"deviceType": "温度传感器",
     * 				"highThreshold": 50,
     * 				"id": 2,
     * 				"lowThreshold": 0,
     * 				"protocol": "LWM2M",
     * 				"upInterval": 10
     *            },
     * 			"sensorName": "温度传感器"
     *        },
     * 		"productCode": "产品编号3",
     * 		"id": 2,
     * 		"manuDate": 1638539522000,
     * 		"pipeModel": {
     * 			"createTime": 1637899002000,
     * 			"fileName": ["clipboard1.png", "clipboard1.png", "clipboard1.png"],
     * 			"fileRelativePath": ["matter/lwjETJDf8/clipboard1.png", "matter/lwjETJDf8/clipboard1.png", "matter/lwjETJDf8/clipboard1.png"],
     * 			"id": 1,
     * 			"pipeIntroduce": "C:/Users/zhoujian/AppData/Local/Temp/tomcat-docbase.4659173895447400607.8003/matter/lwjETJDf8/clipboard1.png",
     * 			"pipeManual": "C:/Users/zhoujian/AppData/Local/Temp/tomcat-docbase.4659173895447400607.8003/matter/lwjETJDf8/clipboard1.png",
     * 			"pipeName": "管道名称1111",
     * 			"pipeNumber": "666",
     * 			"pipePic": "C:/Users/zhoujian/AppData/Local/Temp/tomcat-docbase.4659173895447400607.8003/matter/lwjETJDf8/clipboard1.png",
     * 			"pipeType": "单波纹"
     *        },
     * 		"productDate": 1638539525000,
     * 		"productName": "产品名5",
     * 		"sensorId": 2
     *    }
     * }
     *
     */
    public static HashMap<String, HashMap<String,HashMap<String, Object>>> listToMap(List<Item> items){
        for (Item item:items){
            int id = item.getId();
            HashMap<String, HashMap<String, Object>> hashMapB = new HashMap<>();
            //get pipe
            List<Pipe> pipes = item.getPipes();
            for (Pipe pipe :pipes){
                HashMap<String, Object> hashMapC = new HashMap<>();
                if(pipe != null){
                   hashMapC.put("id",pipe.getId());
                   hashMapC.put("productName",pipe.getProductName());
                   hashMapC.put("productCode",pipe.getProductCode());
                   hashMapC.put("manuDate",pipe.getManuDate());
                   hashMapC.put("productDate",pipe.getProductDate());
                   hashMapC.put("pipeModel",pipe.getPipeModel());

                   //设置一个key为sensorId的键值对
                   Sensor sensor = pipe.getSensor();
                   if (sensor != null) {
                       int sensorId = sensor.getId();
                       hashMapC.put("sensorId",pipe.getSensor().getId());
                       hashMapC.put( String.valueOf(sensorId),pipe.getSensor());
                   }
                    hashMapB.put(String.valueOf(pipe.getId()),hashMapC);
               }
            }
            hashMapA.put(String.valueOf(id),hashMapB);
        }

        return hashMapA;
    }


    /**
     * 遍历数据，获取到key为sensorId的value
     * @param hashMapA
     */
    public static void updateShareHashMap(HashMap<String, HashMap<String,HashMap<String, Object>>> hashMapA,Sensor sensor){
        String id = String.valueOf(sensor.getId());

        //遍历共享数据容器，
        //获取所有的item的key
        Set<String> itemsKeySets = hashMapA.keySet();
        for (String itemsKeySet:itemsKeySets ){
            HashMap<String, HashMap<String, Object>> ItemHashMap = hashMapA.get(itemsKeySet);
            //获取所有pipe的key
            Set<String> pipeKeySets = ItemHashMap.keySet();
            for (String pipeKeySet :pipeKeySets){
                HashMap<String, Object> pipeHashMap = ItemHashMap.get(pipeKeySet);
                //包含sensorId的key
                Set<String> fieldKeySets = pipeHashMap.keySet();
                for (String fieldKeySet:fieldKeySets){
                    //获取sensorMap的key
                        //如果key相等了就赋值
                        if (fieldKeySet.equals(id)){
                            System.out.println( "新添加的传感器报警数据：" + pipeHashMap);
                            pipeHashMap.put(id,sensor);
                            pipeHashMap.put("sensorId",sensor.getId());
                        }
                }
            }
        }
    }

    /**
     * Sensor(id=1, sensorName=压力传感器, sensorCode=1202, protocal=123,
     *
     *        sensorModel=SensorModel(id=1, deviceName=1234设备道, deviceType=压力传感器,
     *                  deviceNumber=1234, createTime=Wed Dec 01 17:24:01 CST 2021, upInterval=10, protocol=MQTT,
     *                  dataPointName=压力, lowThreshold=5, highThreshold=20, dataPointExtra=泄露),
     *
     *        alarm=Alarm(sensorId=1, currentValue=87, alarmMsg=压力异常！当前压力:87, isHandled=false,
     *                      alarmTime=Fri Dec 03 14:35:35 CST 2021))
     * @return
     */
    public static  HashMap<String, Sensor> sensorToMap(Sensor sensor){
        int id = sensor.getId();
        HashMap<String, Sensor> sensorHashMap = new HashMap<>();
        sensorHashMap.put(String.valueOf(id),sensor);
        return sensorHashMap;
    }
}
