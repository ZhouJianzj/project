package com.zj.service;

import com.github.pagehelper.PageHelper;
import com.zj.dao.SysDao;
import com.zj.entity.*;
import com.zj.util.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;


/**
 * @author zhoujian
 */
@Service
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
    public List<Organize> findOrganzieService(String pageNo, String pageSize, String orgName) {
        Integer num = Integer.valueOf(pageNo);
        Integer size = Integer.valueOf(pageSize);
        PageHelper.startPage((num-1)*size,size);
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
            response.setMsg("添加成功！");
            return response;
        }else {
            response.setMsg("添加失败！");
            return  response;
        }

    }

    @Override
    public List<Role> findRoleService() {
        return null;
    }

    @Override
    public boolean deleteRoleService(int id) {
        return sysDao.roleDelete(id);
    }

    /**
     * 角色查询
     * @param pageNo 页码
     * @param pageSize 每一=页数据量
     * @param roleName 角色字段
     * @return 结果集
     */
    @Override
    public List<Role> findRoleService(String pageNo,String pageSize,String roleName) {
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
        return sysDao.permSelect();
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


}
