package com.zj.interceptor;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class CommoInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if (session == null) {
            // 响应结果，响应数据为JSON数据
            response.setCharacterEncoding("utf-8");
            response.setContentType("json; charset=utf-8");
            JSONObject o = new JSONObject();
            PrintWriter writer = response.getWriter();
            o.put("stuts", "400");
            o.put("msg", "没有登录！");
            writer.print(o);
            return false;
        } else if (null == session.getAttribute("user")) {
            // 响应结果，响应数据为JSON数据
            response.setCharacterEncoding("utf-8");
            response.setContentType("json; charset=utf-8");
            JSONObject o = new JSONObject();
            PrintWriter writer = response.getWriter();
            o.put("stuts", "400");
            o.put("msg", "没有登录！");
            writer.print(o);
            return false;
        } else {
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
