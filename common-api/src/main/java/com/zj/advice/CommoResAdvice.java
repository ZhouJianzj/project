package com.zj.advice;

import com.zj.annotation.IgnoreResponseAdvice;
import com.zj.dao.LogDao;
import com.zj.entity.CommonResponse;
import com.zj.entity.Log;
import com.zj.entity.UserManager;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;

/**
 * 设置统一的响应对象，加日志
 */
@RestControllerAdvice
public class CommoResAdvice implements ResponseBodyAdvice<Object> {
    @Resource
    CommonResponse<Object> response;
    @Resource
    private LogDao logDao;

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        //判断请求对象是否使用我们自己的自定义注解，是就忽略增强
        //获取使用在类上面的自定义注解
        if (methodParameter.getDeclaringClass().isAnnotationPresent(
                IgnoreResponseAdvice.class)) {
            return false;
        }
        //获取使用在方法上面的注解
        if (methodParameter.getMethod().isAnnotationPresent(
                IgnoreResponseAdvice.class)) {
            return false;
        }
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o,
                                  MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {

        System.out.println("========================已经进入advice====================");
        //转换
        ServletServerHttpRequest request = (ServletServerHttpRequest) serverHttpRequest;
        HttpServletRequest res = request.getServletRequest();
        //获取状态码
        ServletServerHttpResponse response1 = (ServletServerHttpResponse) serverHttpResponse;
        HttpServletResponse resp = response1.getServletResponse();
        System.out.println("status:" + resp.getStatus());

        //获取登录之后的共享用户对象的名字，（redis session 实现）
        HttpSession session = res.getSession(false);

        //获取状态码来，不是200的直接返回
        if (resp.getStatus() != 200) {
            response.setStatus(resp.getStatus());
            response.setMsg("操作失败！");
            response.setData(null);
            return response;
        } else if (null == o) {
            //没有就执行响应一个初始的响应
            response.setStatus(resp.getStatus());
            response.setMsg("没有响应参数");
            response.setData(null);

        } else if (o instanceof CommonResponse ) {
            response.setStatus(resp.getStatus());
            response.setMsg("success");
            response = (CommonResponse<Object>) o;
        } else {
            //如果是一个普通的数据对象，就传入统一响应对象中去
            response.setStatus(resp.getStatus());
            response.setMsg("success");
            response.setData(o);
        }

        Log log = new Log();
        //获取当前登录的用户名称
        String username = "";

        //确认用户
        UserManager user = (UserManager) session.getAttribute("user");
        if (null != user) {
            username = user.getUsername();
            log.setUsername(username);
        }

        //确认模块,和操作内容
        verifyModule(res.getRequestURI(), log);
        //确认操作类型
        verifyOPerType(res, log);
        //确认操作是否成功
        verifyResult(log);
        //确认时间
        log.setOperTimer(new Date());
        //记录日志
        logDao.logInsert(log);
        return response;
    }

    /**
     * 模块确认
     */
    void verifyModule(String requestURI, Log log) {
        //初始的地址去除/的字段
        String substring = null;
        //不算第一个/出现/的第一个下标
        int end = 0;
        //记录end，方便获取url最后一个字段
        int last = 0;
        //布尔标志
        boolean falg = true;
        //存储字段
        ArrayList<String> array = new ArrayList<>();
        while (falg) {
            last = end;
            //除去开头/第一次出现/前的字段
            end = requestURI.indexOf('/', 1 + end);
            //获取不到/之后会返回值
            if (end == -1) {
                //此时拿取最后一个字段，存储起来
                array.add(requestURI.substring(last + 1));
                //终止循环
                falg = false;
                break;
            }
            //截取
            substring = requestURI.substring(1, end);
            //装载
            array.add(substring);

            substring = null;

        }
        //list大小
        int size = array.size();
        int i = 0;
        String module = null;
        // 示例：/sys/role,获取sys字符串
        switch (array.get(i++)) {
            case "sys":
                module = "系统管理";
                break;
            case "item":
                module = "项目管理";
                break;
            case "user":
                module = "用户管理";
                break;
            case "asset":
                module = "资产管理";
                break;
            case "alarm":
                module = "报警管理";
                break;

            case "model":
                module = "模型管理";
        }
        log.setModuleName(module);

        String content = null;
        if (i >= size) {
            return;
        }
        switch (array.get(i++)) {
            case "login":
                content = "登录操作";
                break;
            case "logout":
                content = "退出操作";
                break;
            case "orga":
                content = "公司操作";
                break;
            case "role":
                content = "角色操作";
                break;
            case "perm":
                content = "权限操作";
                break;
            case "item":
                content = "项目操作";
                break;
            case "log":
                content = "日志操作";
                break;
            case "pipeModel":
                content = "管模操作";
                break;
        }
        log.setOperContent(content);

    }

    /**
     * 解析操作类型
     */
    void verifyOPerType(HttpServletRequest req, Log log) {

        String operType = null;

        switch (req.getMethod()) {
            case "POST":
                operType = "添加";
                break;
            case "DELETE":
                operType = "删除";
                break;
            case "PUT":
                operType = "修改";
                break;
            case "GET":
                operType = "查询";
                break;
        }
        log.setOperType(operType);
    }

    /**
     * 解析操作是否成功
     */
    void verifyResult(Log log) {
        boolean rs = false;
        if (200 == response.getStatus()) {
            rs = true;
        }
        log.setResult(rs);
    }

}
