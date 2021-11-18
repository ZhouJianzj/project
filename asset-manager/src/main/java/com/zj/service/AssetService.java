package com.zj.service;

import com.zj.dao.AssetDao;
import com.zj.entity.CommonResponse;
import com.zj.entity.Pipe;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhoujian
 */
public interface AssetService {

    CommonResponse<Boolean> insertPipeService(Pipe pipe);

    CommonResponse<Boolean> deletePipeService(int id);

    CommonResponse<Boolean> modifyPipeService(Pipe pipe);

    List<Pipe> selectPipeService(String key);
}
