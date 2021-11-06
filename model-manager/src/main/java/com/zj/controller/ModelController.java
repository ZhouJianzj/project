package com.zj.controller;

import com.zj.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhoujian
 * 方法名命名规范
 * -------------------------------------------------------------------------------
 * url规范：
 * 例如：
 *      管道管理的所用url根都是
 *      /pipe
 *      如果根据管道id查询管道
 *      /pipe/id
 * -------------------------------------------------------------------------------
 * controller层类中方法命名规范
 * 使用规范的请求：
 *      PostMapping(新增) DeleteMapping(删除) GetMapping(查询) PutMapping(修改)
 * 动词：
 *      查询 find
 *      添加 add
 *      删除 delete
 *      修改 modify
 * 例如：
 * 添加管道的控制器方法           add  +  pipe  + controller = addPipeController
 * 根据id查询管道的控制器方法      find + pipe  + id + controller = findPipeIdController
 * 删除指定id的管道控制器方法      delete + pipe  + id + controller = deletePipeIdController
 * 修改指定id的官管道的控制器方法   modfiy + pipe  + id + controller = modfiyPipeIdController
 * ------------------------------------------------------------------------------
 * service层类中方法名规范： 动词与控制器中的相同
 * 例如：
 *      addPipeController ------>  addPipeService
 * ------------------------------------------------------------------------------
 * dao层类方法命名规范：
 * 动词：
 *      查询：select
 *      添加：insert
 *      删除：delete
 *      修改：update
 *
 *例如：
 *      添加管道：
 *      addPiepService --------->pipeInsert
 *      pipe + insert = pipeInsert
 *
 *      根据id查询管道：
 *      pipeIdSelect
 *
 */
@RestController
@RequestMapping("model")
public class ModelController {
    @Autowired
    private ModelService modelService;

    //管道模型管理


    //传感器莫模型管理


}
