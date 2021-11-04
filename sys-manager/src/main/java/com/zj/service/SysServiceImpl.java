package com.zj.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zj.dao.SysDao;
import com.zj.entity.*;
import com.zj.util.Md5;
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
        try {
            String ps = Md5.EncoderByMd5(password);
            System.out.println(ps);
            user.setPassword(ps);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //查询
        UserManager userManager = sysDao.userSelect(user);
        //正确的用户放入redissession
        if (null != userManager){
            HttpSession session = request.getSession();
            session.setAttribute("user",userManager);

        }
        return userManager;
    }

    /**
     * 机构查询
     * @param pageNo
     * @param pageSize
     * @param orgName
     * @return
     */
    @Override
    public PageInfo findOrganzieService( String orgName,String pageNo, String pageSize) {
        Integer num = Integer.valueOf(pageNo);
        Integer size = Integer.valueOf(pageSize);
        List<Organize> organizes = sysDao.organizeSelect(orgName);
        PageHelper.startPage(num,size);
        PageInfo pageInfo = new PageInfo(organizes);
        return pageInfo ;
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
     * @param pageNo 页码
     * @param pageSize 每一=页数据量
     * @param roleName 角色字段
     * @return 结果集
     */
    @Override
    public PageInfo<Role> findRoleService(String roleName,String pageNo,String pageSize) {
        Integer no = Integer.valueOf(pageNo);
        Integer size = Integer.valueOf(pageSize);
        PageHelper.startPage(no,size);
        List<Role> roles = sysDao.roleSelect(roleName);
        return new PageInfo<Role>(roles);
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
    public PageInfo<Perm> findPermService(String pageNo,String pageSize) {
        List<Perm> perms = sysDao.permSelect();
        int no = Integer.parseInt(pageNo);
        int size = Integer.parseInt(pageSize);
        PageHelper.startPage(no,size);
        return new PageInfo<Perm>(perms) ;
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
     * @param key
     * @return
     */
    @Override
    public PageInfo<UserManager> finUserService(String key,String pageNo,String pageSize) {
        List<UserManager> userManagers = sysDao.userKeySelect(key);
        Integer no = Integer.valueOf(pageNo);
        Integer size = Integer.valueOf(pageSize);
        PageHelper.startPage(no,size);
        return new PageInfo<UserManager>(userManagers) ;
    }

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @Override
    public UserManager findUserIdService(String id) {
        return sysDao.userIdSelect(id);
    }

    /**
     * 用户添加
     * @param userManager 参数
     * @return 返回值
     * */
    @Override
    public CommonResponse<UserManager> addUserManagerService(UserManager userManager) {
        CommonResponse<UserManager> response = new CommonResponse<>();
        try {
            String password = userManager.getPassword();
            userManager.setPassword(Md5.EncoderByMd5(password));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
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
     * @param pageNo 页码
     * @param pageSize 但一面数据量
     * @return 结果集合
     */
    @Override
    public PageInfo<Log> findLogService(String pageNo,String pageSize) {
        Integer no = Integer.valueOf(pageNo);
        Integer size = Integer.valueOf(pageSize);
        PageHelper.startPage(no,size);
        List<Log> logs = sysDao.logSelect();
        PageInfo<Log> pageInfo = new PageInfo<>(logs);
        return  pageInfo;
    }


}
