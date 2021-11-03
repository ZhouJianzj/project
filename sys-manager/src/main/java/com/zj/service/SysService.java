package com.zj.service;

import com.zj.entity.Organize;
import com.zj.entity.Role;
import com.zj.entity.User;
import com.zj.entity.UserManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author zhoujian
 */
public interface SysService {
     UserManager userLoginService(User user, HttpServletRequest request);

     List<Organize> findOrganzieService(String pageNo, String pageSize, String orgName);

    boolean addOrganizeService(Organize organize);

    List<Role> findRoleService();

    boolean deleteRoleService(int id);
}
