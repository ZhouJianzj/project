package com.zj.service;

import com.zj.entity.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhoujian
 */
public interface SysService {
     User userLogin(User user, HttpServletRequest request);
}
