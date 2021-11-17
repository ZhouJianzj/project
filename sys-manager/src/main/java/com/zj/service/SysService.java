package com.zj.service;

import com.zj.entity.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author zhoujian
 */
public interface SysService {
    UserManager userLoginService(User user, HttpServletRequest request);

    List<Organize> findOrganizeService(String orgName);

    CommonResponse<Object> addOrganizeService(Organize organize);

    CommonResponse<Boolean> deleteRoleService(int id);

    List<Role> findRoleService(String roleName);

    CommonResponse<Object> addRoleService(Role role);

    List<Perm> findPermService();

    Boolean addRolePermService(RolePerm rolePerm);

    List<UserManager> finUserService(String key);

    UserManager findUserIdService(String id);

    CommonResponse<Boolean> addUserManagerService(User user);

    CommonResponse<Boolean> deleteUserManagerService(int id);

    List<Log> findLogService();

    CommonResponse<Object> signOutService(HttpServletRequest request);

    Boolean modifyUserService(User user);

    CommonResponse<Boolean> modifyPasswordService(UserManager userManager);

    Boolean findUserNameService(String name);

    CommonResponse<Boolean> deletePermService(int id);

    CommonResponse<Boolean> insertPermService(Perm perm);

    CommonResponse<Boolean> modifyPermService(Perm perm);

    List<UserManager> selectUserByOrgaIdService(int orgaId);
}
