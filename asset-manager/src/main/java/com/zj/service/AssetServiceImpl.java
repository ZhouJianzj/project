package com.zj.service;

import com.zj.dao.AssetDao;
import com.zj.entity.CommonResponse;
import com.zj.entity.Pipe;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author HuXinJie
 * @version 1.0
 * @date 2021/11/18 8:55
 */
@Service
public class AssetServiceImpl implements AssetService {
    @Resource
    private AssetDao assetDao;

    @Override
    public CommonResponse<Boolean> insertPipeService(Pipe pipe) {
        CommonResponse<Boolean> response = new CommonResponse<>();
        if (assetDao.pipeInsert(pipe)){
            response.setMsg("新增成功");
            response.setStatus(200);
        }else {
            response.setMsg("新增失败");
            response.setStatus(400);
        }
        return response;
    }

    @Override
    public CommonResponse<Boolean> deletePipeService(int id) {
        CommonResponse<Boolean> response = new CommonResponse<>();
        if (assetDao.pipeDelete(id)){
            response.setMsg("删除成功");
            response.setStatus(200);
        }else {
            response.setMsg("删除失败");
            response.setStatus(400);
        }
        return response;
    }

    @Override
    public CommonResponse<Boolean> modifyPipeService(Pipe pipe) {
        CommonResponse<Boolean> response = new CommonResponse<>();
        if (assetDao.pipeModify(pipe)){
            response.setStatus(200);
            response.setMsg("修改成功");
        }else {
            response.setStatus(400);
            response.setMsg("修改失败");
        }
        return response;
    }

    @Override
    public List<Pipe> selectPipeService(String key) {
        return assetDao.pipeSelect(key);
    }
}
