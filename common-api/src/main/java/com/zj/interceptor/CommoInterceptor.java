package com.zj.interceptor;

import com.alibaba.fastjson.JSON;
import com.zj.entity.UserManager;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

public class CommoInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if ( session == null){

            // 响应结果，响应数据为JSON数据
            HashMap data = new HashMap();
            DataBuffer buffer = null;
                data.put("code", 404);
                data.put("msg", "no login, please login!");
                response.getWriter().write(String.valueOf(data));
           return false;
       }else {
            UserManager user = (UserManager) session.getAttribute("user");
            return true;
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {


    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
