package com.zj.service;

import com.zj.dao.AlarmDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhoujian
 */
@Service
public class AlarmService{
    @Resource
    private AlarmDao alarmDao;
}
