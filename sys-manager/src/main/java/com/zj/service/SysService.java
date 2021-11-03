package com.zj.service;

import com.zj.entity.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author zhoujian
 */
public interface SysService {
     UserManager userLoginService(User user, HttpServletRequest request);

     List<Organize> findOrganzieService(String pageNo, String pageSize, String orgName);

    CommonResponse<Object> addOrganizeService(Organize organize);

    List<Role> findRoleService();

    boolean deleteRoleService(int id);
    List<Role> findRoleService(String pageNo,String pageSize,String roleName);

    CommonResponse<Object> addRoleService(Role role);

    List<Perm> findPermService();

    CommonResponse<Object> addRolePermService(String roleId, String permId);
}
