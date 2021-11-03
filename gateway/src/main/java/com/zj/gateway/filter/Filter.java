package com.zj.gateway.filter;


import com.alibaba.fastjson.JSON;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author zhoujian
 * 全局的过滤器实现权限验证
 */
@Component
public class Filter implements GlobalFilter, Ordered {
    private static final String PATH = "/sys/login";
    private static final String CHARSET_NAME = "utf-8";



    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        //获取session
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        String path = request.getPath().toString();


        // 判断是否为/sys/login
        if (path.contains(PATH)) {
            //  请求路径中含有/login,则放行
            return chain.filter(exchange);
        }
        // 登录之后就放行,获取共享的session，有就放行，没有实现
        else if (true) {

            return chain.filter(exchange);
        }
        //   什么都没有则返回友好提示页面
        else {
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

//        Map<String, Object> sessionMap = new HashMap<>();
//        sessionMap.put("param", "Hi, I am the value from cloud gateway");
//        Mono<WebSession> webSessionMono = exchange.getSession()
//                .doOnNext(new Consumer<WebSession>() {
//                    @Override
//                    public void accept(WebSession webSession) {
//                        webSession.getAttributes().putAll(sessionMap);
//                    }
//                });


    }

}


