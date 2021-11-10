package com.zj.service;

import com.zj.dao.SysDao;
import com.zj.entity.*;
import com.zj.util.ListToTree;
import com.zj.util.MD5Util;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * @author zhoujian
 */
@Service
public class SysServiceImpl implements SysService {
    @Resource
    private SysDao sysDao;

    /**
     * 用户登录
     */
    @Override
    public UserManager userLoginService(User user, HttpServletRequest request) {
        String password = user.getPassword();
        //md5加密
        //用户传输过来的是123
        String s = MD5Util.addMD5(password);
        user.setPassword(s);
        //查询
        UserManager userManager = sysDao.userSelect(user);
        //正确的用户放入redissession
        if (null != userManager) {
            HttpSession session = request.getSession();
            //获取查询到的用户对密码两次解密
            String s1 = MD5Util.solveMD5(MD5Util.solveMD5(password));
            userManager.setPassword(s1);
            System.out.println(s1);
            session.setAttribute("user", userManager);
        }
        return userManager;
    }


    /**
     * 机构查询
     */
    @Override
    public List<Organize> findOrganzieService(String orgName) {

        return sysDao.organizeSelect(orgName);
    }

    /**
     * 添加机构
     *
     * @return
     */
    @Override
    public CommonResponse<Object> addOrganizeService(Organize organize) {
        CommonResponse<Object> response = new CommonResponse<>();
        if (sysDao.organizeInsert(organize)) {
            response.setStatus(200);
            response.setMsg("添加成功！");
            return response;
        } else {
            response.setStatus(400);
            response.setMsg("添加失败！");
            return response;
        }

    }


    /**
     * 删除角色
     * 首先删除角色表中的角色，
     * 然后去role_perm表中删除对应关系
     * 然后再去user_role中删除对应的关系
     * @param id
     * @return
     */
    @Override
    @Transactional
    public CommonResponse<Boolean> deleteRoleService(int id) {
        CommonResponse<Boolean> response = new CommonResponse<>();
        //删除角色表中的对应的角色id
        if (sysDao.roleDelete(id)) {
            //用户user_role查询，查到就删除
            if (sysDao.roleUserSelect(id).size() != 0) {
                sysDao.roleUserDelete(id);
            }
            // 查询role_perm表中对应的关系，查到就删
            if (sysDao.rolePermSelect(id).size() != 0) {
                sysDao.rolePermDelete(id);
            }
            response.setStatus(200);
            response.setMsg("删除成功");
        } else {
            response.setStatus(400);
            response.setMsg("删除失败");

        }
        return response;
    }

    /**
     * 角色查询
     *
     * @param roleName 角色字段
     * @return 结果集
     */
    @Override
    public List<Role> findRoleService(String roleName) {
        List<Role> roleList =  sysDao.roleSelect(roleName);
        return roleList;

    }

    /**
     * 角色添加
     *
     * @param role 参数
     * @return 返回结果
     */
    @Override
    public CommonResponse<Object> addRoleService(Role role) {
        CommonResponse<Object> response = new CommonResponse<>();
        if (role.getName() != null && role.getExt() != null && sysDao.roleInsert(role)) {
            response.setStatus(200);
            response.setMsg("添加成功！");
            return response;
        } else {
            response.setStatus(400);
            response.setMsg("添加失败！");
            return response;
        }
    }

    /**
     * 查询所有权限
     *
     * @return 权限结果集
     */
    @Override
    public List<Perm> findPermService() {
        List<Perm> perms = sysDao.permSelect();
        ListToTree tree = new ListToTree(perms);
        perms = tree.buildTree();
        return perms;
    }

    /**
     * 给角色添加权限
     */
    @Override
    public CommonResponse<Object> addRolePermService(String roleId, String permId) {
        CommonResponse<Object> response = new CommonResponse<>();
        if (sysDao.rolePermInsert(roleId, permId)) {
            response.setStatus(200);
            response.setMsg("添加成功！");
            return response;
        } else {
            response.setStatus(400);
            response.setMsg("添加失败！");
            return response;
        }
    }


    /**
     * 查询用户，支持模糊查询，查询关键字可以是手机号或者是用户名
     */
    @Override
    public List<UserManager> finUserService(String Key) {
        List<UserManager> userManagers = null;
        if (Key == null || Key == "") {
            userManagers = sysDao.allUserSelect();
        } else {
            userManagers = sysDao.userKeySelect(Key);
        }
        return userManagers;
    }

    /**
     * 根据id查询用户
     *
     * @param id
     * @return
     */
    @Override
    public UserManager findUserIdService(String id) {
        return sysDao.userIdSelect(id);
    }

    /**
     * 用户添加
     */
    @Override
    public CommonResponse<Boolean> addUserManagerService(User user) {
        CommonResponse<Boolean> response = new CommonResponse<>();
//      对新增用户的密码进行加密操作
        String s = MD5Util.addMD5(user.getPassword());
        //密码加密
        user.setPassword(s);
        //获取角色id
        Integer[] roleIdArrays = user.getRoleIdArrays();
        //user表插入用户
        if (sysDao.userManagerInsert(user)) {
            //新增user的自增主键
            int userid = sysDao.useridGet();
            //获取role id 循环添加
            for (int i = 0; i < roleIdArrays.length; i++) {
                sysDao.userRoleInsert(userid, roleIdArrays[i]);
            }
            response.setMsg("添加成功");
            response.setStatus(200);
        } else {
            response.setStatus(400);
            response.setMsg("添加失败");
        }
        return response;
    }

    /**
     * 修改用户 修改user表中的username phone
     * 先查询user_role中是否已经有对应的关系然后
     * 添加 user_role 中的对应关系
     */
    @Override
    @Transactional
    public Boolean modifyUserService(User user) {

        //修改user   name 和  phone
        if (user.getUsername() != null && user.getPhone() != null) {
            //修改user表
            sysDao.userUpdate(user);
            //修改user的 orgaId，如何传来的是空就表示去除用户的组织
            sysDao.userUpdateOrgaId(user.getId(), user.getOrgaId());
        }

        Integer[] roleId = user.getRoleIdArrays();
        //添加user_role表中的对应权限
        if (roleId.length != 0) {
            //先全部删除
            sysDao.userRoleDelete(user.getId());
            Boolean insert = false;
            //在依次的添加
            for (int i = 0; i < roleId.length; i++) {
                    //取决于sql操作
                insert =  sysDao.userInsertRole(user.getId(), roleId[i]);

            }
            return insert;

        }else {
           return sysDao.userRoleDelete(user.getId());
        }



    }

    /**
     * 修改密码
     */
    @Override
    public CommonResponse<Boolean> modifyPasswordService(int id, String password) {
        password = MD5Util.addMD5(password);
        CommonResponse<Boolean> response = new CommonResponse<>();
        if (sysDao.passwordModify(id, password)) {
            response.setMsg("修改密码成功");
            response.setStatus(200);
        } else {
            response.setMsg("修改密码失败");
            response.setStatus(400);
        }
        return response;
    }

    /**
     * 删除用户，首先删除用户表，然后根据user的id到user_role表中删除对应的关系
     */
    @Override
    @Transactional
    public CommonResponse<Boolean> deleteUserManagerService(int id) {
        CommonResponse<Boolean> response = new CommonResponse<>();
        //到user表中删除对应id的user   删除user_role中删除对应的关系
        if (sysDao.userDelete(id) && sysDao.userRoleDelete(id)) {
            response.setMsg("删除成功");
            response.setStatus(200);
        } else {
            response.setStatus(400);
            response.setMsg("删除失败");
        }
        return response;

    }


    /**
     * 实现log分页查询
     *
     * @return 结果集合
     */
    @Override
    public List<Log> findLogService() {

        return sysDao.logSelect();

    }

    /**
     * 退出登录
     */
    @Override
    public CommonResponse<Object> signOutService(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (null != session) {
            session.removeAttribute("user");
            return new CommonResponse<>(200, "用户退出登录！");
        }
        return new CommonResponse<>(401, "退出登录失败！");
    }


}
