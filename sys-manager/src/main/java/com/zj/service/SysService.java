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

    PageInfo findOrganzieService( String orgName,String pageNo, String pageSize);

    CommonResponse<Object> addOrganizeService(Organize organize);

    CommonResponse<Boolean> deleteRoleService(int id);

    PageInfo<Role> findRoleService(String roleName,String pageNo,String pageSize);

    CommonResponse<Object> addRoleService(Role role);

    PageInfo<Perm> findPermService( String pageNo,String pageSize);

    CommonResponse<Object> addRolePermService(String roleId, String permId);

    PageInfo<UserManager> finUserService(String key,String pageNo,String pageSize);

    UserManager findUserIdService(String id);

    CommonResponse<UserManager> addUserManagerService(UserManager userManager);

    CommonResponse<Boolean> deleteUserManagerService(int id);

    PageInfo<Log> findLogService(String pageNo, String pageSize);
}
