package com.zj.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zj.annotation.IgnoreResponseAdvice;
import com.zj.entity.*;
import com.zj.service.SysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    public PageInfo<Organize> findOrganizeController(String orgName,String pageNo,String pageSize){
        PageHelper.startPage(Integer.valueOf(pageNo),Integer.valueOf(pageSize));

        return new PageInfo<Organize>(sysService.findOrganzieService(orgName)) ;
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
    public PageInfo<Role> findRoleController(String roleName,String pageNo,String pageSize){
        PageHelper.startPage(Integer.valueOf(pageNo),Integer.valueOf(pageSize));
        return new PageInfo<>(sysService.findRoleService( roleName));
    }

    /**
     * 添加角色
     * @param role 添加对象参数
     * @return 返回结果
     */
    @PostMapping("role")
    public CommonResponse<Object> addRoleController(@RequestBody Role role){
        return sysService.addRoleService(role);
    }

    /**
     * 删除角色
     * */
    @DeleteMapping("role")
    public CommonResponse<Boolean> deleteRoleController( int id){
        return sysService.deleteRoleService(id);
    }

    /**
     * 权限查询
     * @return 权限结果集
     */
    @GetMapping("perm")
    public PageInfo<Perm> findPermController(@RequestParam("pageNo") String pageNo,@RequestParam("pageSize") String pageSize){
        PageHelper.startPage(Integer.valueOf(pageNo),Integer.valueOf(pageSize));
        return  new PageInfo<Perm>(sysService.findPermService());

    }

    /**
     * 角色分配权限
     * @param roleId 角色id
     * @param permId 权限id
     * @return 统一响应体
     */
    @PostMapping("role/perm")
    public CommonResponse<Object> addRolePermController(String roleId,String permId){
        return sysService.addRolePermService(roleId,permId);
    }


    /**
     * 查询所有的用户
     * @param key 手机号 或者 用户名
     * @return
     */
    @GetMapping("user/key")
    public PageInfo<UserManager> findUserController(String key,String pageNo,String pageSize){
        PageHelper.startPage(Integer.valueOf(pageNo),Integer.valueOf(pageSize));
        return new PageInfo<UserManager>(sysService.finUserService(key));
    }

    /**
     * 根据id查询用户
     * @param id 用户id
     * @return 结果集
     */
    @GetMapping("user/id")
    public UserManager findUserIdController(String id){
        return sysService.findUserIdService(id);
    }

    /**
     * 新增用户
     * @param userManager 参数对象
     * @return 统一响应体
     * */
    @PostMapping("user")
    public CommonResponse<UserManager> addUserManagerController(@RequestBody UserManager userManager){
        return sysService.addUserManagerService(userManager);
    }

    /**
     * 删除用户
     * @param id 用户地
     * @return 统一响应体
     * */
    @DeleteMapping("user")
    public CommonResponse<Boolean> deleteUserManagerController(int id){
        return sysService.deleteUserManagerService(id);
    }

    /**
     * 查询日志
     * @param pageNo  页码
     * @param pageSize 单页面数据量
     * @return  数据
     */
    @GetMapping("log")
    public PageInfo<Log> findLogController(String pageNo, String pageSize){
        PageHelper.startPage(Integer.valueOf(pageNo),Integer.valueOf(pageSize));

         return new PageInfo<Log>(sysService.findLogService()) ;
    }

    /**
     * 退出登录
     */
    @GetMapping("logout")
    public CommonResponse<Object> signOutController(HttpServletRequest request){
        return sysService.signOutService(request);

    }
}