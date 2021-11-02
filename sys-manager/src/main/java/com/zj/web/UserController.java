package com.zj.web;

import com.zj.annotation.IgnoreResponseAdvice;
import com.zj.entity.User;
import com.zj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author zhoujian
 */
@RestController
@RequestMapping("sys")
@IgnoreResponseAdvice
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 测试网关
     * @return
     */
    @RequestMapping("test")
    public String test(){
        return "test";
    }

    /**
     * 用户登录
     * @return
     */
    @PostMapping("login")
    public User userLoginController(@RequestBody User user, HttpServletRequest request){
        System.out.println(user.toString());
        return userService.userLogin(user,request);
    }

}