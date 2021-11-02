package com.zj.web;

import com.zj.annotation.IgnoreResponseAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhoujian
 */
@RestController
@RequestMapping("sys")
public class UserController {

    @RequestMapping("test")
    @IgnoreResponseAdvice
    public String test(){
        return "test";
    }
}