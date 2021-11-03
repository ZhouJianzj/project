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
     * @return
     */
    @IgnoreResponseAdvice
    @RequestMapping("test")
    public String test(){
        return "test";
    }

    /**
     * 用户登录
     * @return
     */
    @PostMapping("login")
    public UserManager userLoginController(@RequestBody User user, HttpServletRequest request){
        return sysService.userLoginService(user,request);
    }

    /**
     * 公司查询，支持模糊查询和分页
     * @param pageNo
     * @param pageSize
     * @param orgName
     * @return
     */
    @GetMapping("orga")
    public List<Organize> findOrganizeController(String pageNo,String pageSize,String orgName){
        return sysService.findOrganzieService(pageNo,pageSize,orgName);
    }

    /**
     * 添加机构
     * @param organize
     * @return
     */
    @PostMapping("orga")
    public CommonResponse<Object> addOrganizeController(@RequestBody Organize organize){
        CommonResponse<Object> response = new CommonResponse<>();
       if (sysService.addOrganizeService(organize)){
             response.setMsg("添加成功！") ;
       }else {
            response.setMsg("添加失败");
       }
        return  response;
    }


    public List<Role> findRoleController(String pageNo,String pageSize,String roleName){
        return sysService.findRoleService();
    }

    @DeleteMapping("role/{id}")
    public CommonResponse<Boolean> deleteRoleController(@PathVariable("id") int id)throws Exception{
        CommonResponse<Boolean> response = new CommonResponse<>();
        boolean flag = sysService.deleteRoleService(id);
        if (flag != false){
            response.setMsg("删除成功1");
        }else {
            response.setMsg("删除错误！");
        }
        return response;
    }
}