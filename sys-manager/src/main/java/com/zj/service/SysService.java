package com.zj.service;

import com.zj.entity.User;
import com.zj.entity.UserManager;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhoujian
 */
public interface SysService {
     UserManager userLogin(User user, HttpServletRequest request);
}
