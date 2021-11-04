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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;

;import java.util.ArrayList;
import java.util.Date;

/**
 * 设置统一的响应对象
 */
@RestControllerAdvice
public class CommoResAdvice implements ResponseBodyAdvice<Object> {
    @Resource
    private LogDao logDao;

    //默认的响应对象
    CommonResponse<Object> response = new CommonResponse<Object>();

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        //判断请求对象是否使用我们自己的自定义注解，是就忽略增强
        //获取使用在类上面的自定义注解
        if (methodParameter.getDeclaringClass().isAnnotationPresent(
                IgnoreResponseAdvice.class)){
            return false;
        }
        //获取使用在方法上面的注解
        if (methodParameter.getMethod().isAnnotationPresent(
                IgnoreResponseAdvice.class)){
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

        System.out.println("========================已经进入advice");
        //转换
        ServletServerHttpRequest request = (ServletServerHttpRequest) serverHttpRequest;
        HttpServletRequest res = request.getServletRequest();
        //获取登录之后的共享用户对象的名字，（redis session 实现）
        HttpSession session = res.getSession(false);
         if (null == o){
            //没有就执行响应一个初始的响应
            response.setStatus(200);
            response.setMsg("没有响应参数");

        }else if ( o instanceof ResponseBody || o instanceof  CommonResponse){
            response.setStatus(200);
            response.setMsg("success");
            //如有已经是一个统一的响应对象了就转一下
            response = (CommonResponse<Object>) o;
        }else {
            //如果是一个普通的数据对象，就传入统一响应对象中去
            response.setStatus(200);
            response.setMsg("success");
            response.setData(o);
        }


        Log log = new Log();
        //获取当前登录的用户名称
        String username = "";

        //确认用户
        UserManager user = (UserManager) session.getAttribute("user");
        if (null != user){
            username =user.getUsername();
        }

        log.setUsername(username);

        //确认模块
         verifyModule(res.getRequestURI(),log);
        //确认操作类型
         verifyOPerType(res,log);
        //确认操作是否成功
         verifyResult(log);
         //确认时间
         log.setOperTimer(new Date());

         logDao.logInsert(log);

        return response;
    }

    /**
     * 模块确认
     */
    void verifyModule(String requestURI,Log log ){

        String substring = null;
        int end = 0;
        int last = 0 ;
        boolean falg = true;
        ArrayList<String> array = new ArrayList<>();
        while (falg){
             last = end;
             end = requestURI.indexOf('/', 1 + end);

            if (end == -1){
                 array.add(requestURI.substring(last + 1));
                falg = false;
                break;
            }
            substring = requestURI.substring(1, end);

            array.add(substring);

            substring = null;

        }

        int size = array.size();
        int i = 0;
        String module = null;
        // 示例：/sys/role,获取sys字符串
        switch (array.get(i++)){
            case "sys":
                module="系统管理";
                break;
            case "item":
                module="项目管理";
                break;
            case "user":
                module="用户管理";
                break;
            case "dev":
                module="资产管理";
                break;
            case "alarm":
                module="报警管理";
        }
        log.setModuleName(module);

        String content = null;
        if (i >= size) return;
        switch (array.get(i++)){
            case "login":
                content="登录操作";
                break;
            case "orga":
                content="公司操作";
                break;
            case "role":
                content="角色操作";
                break;
            case "perm":
                content="权限操作";
                break;
        }
        log.setOperContent(content);

    }

    /**
     * 解析操作类型
     */
    void verifyOPerType(HttpServletRequest req,Log log){

        String operType=null;

        switch (req.getMethod()){
            case "POST":
                operType="添加";
                break;
            case "DELETE":
                operType="删除";
                break;
            case "PUT":
                operType="修改";
            case "GET":
                operType="查询";
        }
        log.setOperType(operType);
    }

    /**
     *解析操作是否成功
     */
    void verifyResult(Log log){
        boolean rs=false;
        if (200 == response.getStatus()){
            rs = true;
        }
        log.setResult(rs);
    }

}
