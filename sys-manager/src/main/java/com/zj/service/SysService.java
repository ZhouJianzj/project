package com.zj.service;

import com.github.pagehelper.PageInfo;
import com.zj.entity.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author zhoujian
 */
public interface SysService {
     UserManager userLoginService(User user, HttpServletRequest request);

    List<Organize> findOrganzieService( String orgName);

    CommonResponse<Object> addOrganizeService(Organize organize);

    CommonResponse<Boolean> deleteRoleService(int id);

    List<Role> findRoleService(String roleName);

    CommonResponse<Object> addRoleService(Role role);

    List<Perm> findPermService();

    CommonResponse<Object> addRolePermService(String roleId, String permId);

    List<UserManager> finUserService(String key);

    UserManager findUserIdService(String id);

    CommonResponse<UserManager> addUserManagerService(UserManager userManager);

    CommonResponse<Boolean> deleteUserManagerService(int id);

    List<Log> findLogService();

    CommonResponse<Object> signOutService(HttpServletRequest request);
}
