package com.zj.service;

import com.zj.entity.Organize;
import com.zj.entity.User;
import com.zj.entity.UserManager;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhoujian
 */
public interface SysService {
     UserManager userLoginService(User user, HttpServletRequest request);

     Organize findOrganzieService(String pageNo,String orgName);
}
