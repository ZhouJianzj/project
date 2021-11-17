package com.zj.controller;


import com.zj.service.AssetService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhoujian
 */
@RestController
@RequestMapping("asset")
public class AssetController {
    @Resource
    private AssetService assetService;
}
