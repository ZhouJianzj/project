package com.zj.controller;


import com.zj.entity.CommonResponse;
import com.zj.entity.Page;
import com.zj.entity.Pipe;
import com.zj.service.AssetService;
import com.zj.util.MyPageHelper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
     * */
    @GetMapping("pipe")
    public Page selectPipeController( @RequestParam(value = "pageNo", defaultValue = "1") String pageNo,
                                      @RequestParam(value = "pageSize", defaultValue = "8") String pageSize,
                                      @RequestParam("key") String key){
        return MyPageHelper.myPageHelper(new Page(assetService.selectPipeService(key),Integer.parseInt(pageNo),Integer.parseInt(pageSize)));
    }
}
