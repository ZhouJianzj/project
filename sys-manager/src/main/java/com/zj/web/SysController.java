package com.zj.web;

import com.zj.annotation.IgnoreResponseAdvice;
import com.zj.entity.Organize;
import com.zj.entity.User;
import com.zj.entity.UserManager;
import com.zj.service.SysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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

    @GetMapping("orga")
    public Organize findOrganizeController(String pageNo,String orgName){
        return sysService.findOrganzieService(pageNo,orgName);
    }
}