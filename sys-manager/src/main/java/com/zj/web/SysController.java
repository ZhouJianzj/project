package com.zj.web;

import com.zj.annotation.IgnoreResponseAdvice;
import com.zj.entity.*;
import com.zj.service.SysService;
import com.zj.util.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * @author zhoujian
 */
@RestController
@RequestMapping("sys")

public class SysController {
    @Autowired
    private SysService sysService;

    /**
     * 测试网关
     * @return 测试网关
     */
    @IgnoreResponseAdvice
    @RequestMapping("test")
    public String test(){
        return "test";
    }

    /**
     * 用户登录
     * @return 用户对象
     */
    @PostMapping("login")
    public UserManager userLoginController(@RequestBody User user, HttpServletRequest request){
        return sysService.userLoginService(user,request);
    }

    /**
     * 公司查询，支持模糊查询和分页
     * @param pageNo 页码
     * @param pageSize 页面数据量
     * @param orgName 查询关键字
     * @return 结果集
     */
    @GetMapping("orga")
    public List<Organize> findOrganizeController(String pageNo,String pageSize,String orgName){
        return sysService.findOrganzieService(pageNo,pageSize,orgName);
    }

    /**
     * 添加机构
     * @param organize 插入参数
     * @return 插入是否成功
     */
    @PostMapping("orga")
    public CommonResponse<Object> addOrganizeController(@RequestBody Organize organize){
        return  sysService.addOrganizeService(organize);
    }

    /**
     * 角色查询
     * @param pageNo 页码
     * @param pageSize 每一个展示的数据
     * @param roleName 查询关键字
     * @return role结果集
     */
    @GetMapping("role")
    public List<Role> findRoleController(String pageNo,String pageSize,String roleName){
        return sysService.findRoleService( pageNo, pageSize, roleName);
    }

    /**
     * 添加角色
     * @param role 添加对象参数
     * @return 返回结果
     */
    @PostMapping("role")
    public CommonResponse<Object> addRoleController( @RequestBody Role role){
        return sysService.addRoleService(role);
    }

    @GetMapping("perm")
    public List<Perm> findPermController(){
        return sysService.findPermService();
    }
}