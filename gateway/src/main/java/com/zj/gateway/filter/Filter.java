package com.zj.gateway.filter;


import com.alibaba.fastjson.JSON;
import org.apache.catalina.User;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpSession;
import java.net.URI;
import java.util.HashMap;

/**
 * @author zhoujian
 * 全局的过滤器实现权限验证
 */
@Component
public class Filter implements GlobalFilter, Ordered {
    private static final String PATH = "/sys/login";
    private static final String CHARSET_NAME = "utf-8";
    private static final String TOKEN = "true";

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取session
        ServerHttpRequest request = exchange.getRequest();

        ServerHttpResponse response = exchange.getResponse();

        // 获取请求路径
        String path = request.getPath().toString();
        System.out.println("path:" + path + "=====================");

        HttpSession session = (HttpSession) exchange.getSession();

        // 判断是否请求的login页面
        if (path.contains(PATH)) {
            //  请求路径中含有/login,则放行
            return chain.filter(exchange);
            // 登录之后就放行
        } else if (null != session) {
            User user = (User) session.getAttribute("user");
            if (null != user) {
                System.out.println(user.toString());
            }
            return chain.filter(exchange);
        } else {
            // 响应结果，响应数据为JSON数据
            HashMap data = new HashMap();
            DataBuffer buffer = null;
            try {
                data.put("code", 404);
                data.put("msg", "请先登录");
                byte[] bytes = JSON.toJSONString(data).getBytes(CHARSET_NAME);
                buffer = response.bufferFactory().wrap(bytes);
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return response.writeWith(Mono.just(buffer));
        }

    }
}


