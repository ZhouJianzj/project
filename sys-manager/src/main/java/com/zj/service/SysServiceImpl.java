package com.zj.service;

import com.zj.dao.SysDao;
import com.zj.entity.*;
import com.zj.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;


/**
 * @author zhoujian
 */
@Service
@Transactional
public class SysServiceImpl implements SysService {
    @Autowired
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
            System.out.println(s);
            user.setPassword(s);

        //查询
        UserManager userManager = sysDao.userSelect(user);
        //正确的用户放入redissession
        if (null != userManager){
            HttpSession session = request.getSession();
            //获取查询到的用户对密码两次解密
            userManager.setPassword(MD5Util.solveMD5(MD5Util.solveMD5(userManager.getPassword())));
            session.setAttribute("user",userManager);
        }
        return userManager;
    }

    /**
     * 机构查询
     *
     * @param orgName
     * @return
     */
    @Override
    public List<Organize> findOrganzieService(String orgName) {

        return sysDao.organizeSelect(orgName);
    }

    /**
     * 添加机构
     * @return
     */
    @Override
    public CommonResponse<Object> addOrganizeService(Organize organize) {
        CommonResponse<Object> response = new CommonResponse<>();
        if (sysDao.organizeInsert(organize)){
            response.setStatus(200);
            response.setMsg("添加成功！");
            return response;
        }else {
            response.setStatus(400);
            response.setMsg("添加失败！");
            return  response;
        }

    }


    /**
     * 删除角色
     * @param id
     * @return
     */
    @Override
    public CommonResponse<Boolean> deleteRoleService(int id) {
        CommonResponse<Boolean> response = new CommonResponse<>();
        boolean b = sysDao.roleDelete(id);
        if (b == false){
            response.setStatus(400);
            response.setMsg("删除失败");
        }else {
            response.setStatus(200);
            response.setMsg("删除成功");
        }
        return response;
    }

    /**
     * 角色查询
     * @param roleName 角色字段
     * @return 结果集
     */
    @Override
    public List<Role> findRoleService(String roleName) {
        return sysDao.roleSelect(roleName);
    }

    /**
     * 角色添加
     * @param role 参数
     * @return 返回结果
     */
    @Override
    public CommonResponse<Object> addRoleService(Role role) {
        CommonResponse<Object> response = new CommonResponse<>();
        if (sysDao.roleInsert(role)){
            response.setStatus(200);
            response.setMsg("添加成功！");
            return response;
        }else {
            response.setStatus(400);
            response.setMsg("添加失败！");
            return  response;
        }
    }

    /**
     * 查询所有权限
     * @return 权限结果集
     */
    @Override
    public List<Perm> findPermService() {
        List<Perm> perms = sysDao.permSelect();
        return perms;
    }

    /**
     * 给角色添加权限
     * @param roleId
     * @param permId
     * @return
     */
    @Override
    public CommonResponse<Object> addRolePermService(String roleId, String permId) {
        CommonResponse<Object> response = new CommonResponse<>();
        if (sysDao.rolePermInsert(roleId,permId)){
            response.setStatus(200);
            response.setMsg("添加成功！");
            return response;
        }else {
            response.setStatus(400);
            response.setMsg("添加失败！");
            return response;
        }
    }

    /**
     * 查询用户，支持模糊查询，查询关键字可以是手机号或者是用户名
     *
     */
    @Override
    public List<UserManager> finUserService(String Key) {
        List<UserManager> userManagers = null;
        if (Key == null || Key == ""){
            userManagers = sysDao.allUserSelect();
            //给用户解密
            for (UserManager userManager :userManagers){
                userManager.setPassword(MD5Util.solveMD5(MD5Util.solveMD5(userManager.getPassword())));
            }
        }
        else {
            userManagers = sysDao.userKeySelect(Key);
            //给用户解密
            for (UserManager userManager :userManagers){
                userManager.setPassword(MD5Util.solveMD5(MD5Util.solveMD5(userManager.getPassword())));
            }

        }
        return userManagers ;
    }



    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @Override
    public UserManager findUserIdService(String id) {
        UserManager userManager = sysDao.userIdSelect(id);
        //给用户解密
        userManager.setPassword(MD5Util.solveMD5(MD5Util.solveMD5(userManager.getPassword())));
        return userManager;
    }

    /**
     * 用户添加
     * @param userManager 参数
     * @return 返回值
     * */
    @Override
    public CommonResponse<UserManager> addUserManagerService(UserManager userManager) {
        CommonResponse<UserManager> response = new CommonResponse<>();
        //获取新增用户密码，转成MD5
        userManager.setPassword( MD5Util.addMD5(userManager.getPassword()));
        if (sysDao.userManagerInsert(userManager)){
            response.setMsg("添加成功");
            response.setStatus(200);
        }else {
            response.setStatus(400);
            response.setMsg("添加失败");
        }
        return response;
    }

    /**
     * 删除用户
     * @param id
     * @return
     * */
    @Override
    public CommonResponse<Boolean> deleteUserManagerService(int id) {
        CommonResponse response = new CommonResponse();
        if (sysDao.userManagerDelete(id)){
            response.setMsg("删除成功");
            response.setStatus(200);
        }else {
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
        if (null != session){
            session.removeAttribute("user");
            return new CommonResponse<>(200,"用户退出登录！");
        }
        return new CommonResponse<>(401,"退出登录失败！");
    }

    /**
     * 修改用户
     * @return
     */
    @Override
    public Boolean modifyUserService(User user) {
        return sysDao.userUpdate(user);
    }


}
