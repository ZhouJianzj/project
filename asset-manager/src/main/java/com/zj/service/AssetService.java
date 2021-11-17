package com.zj.service;

import com.zj.dao.AssetDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhoujian
 */
@Service
public class AssetService {
    @Resource
    private AssetDao assetDao;
}
