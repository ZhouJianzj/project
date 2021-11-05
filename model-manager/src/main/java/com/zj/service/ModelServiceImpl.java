package com.zj.service;

import com.zj.dao.ModelDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhoujian
 */
@Service
public class ModelServiceImpl implements ModelService {
    @Autowired
    private ModelDao modelDao;


}
