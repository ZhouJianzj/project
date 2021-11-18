package com.zj.service;

import com.zj.dao.AssetDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author HuXinJie
 * @version 1.0
 * @date 2021/11/18 8:55
 */
@Service
public class AssetServiceImpl implements AssetService {
    @Resource
    private AssetDao assetDao;
}
