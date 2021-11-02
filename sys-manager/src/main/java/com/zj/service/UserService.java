package com.zj.service;

import com.zj.entity.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhoujian
 */
public interface UserService {
     User userLogin(User user, HttpServletRequest request);
}
