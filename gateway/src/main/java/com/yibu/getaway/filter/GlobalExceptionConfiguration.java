package com.yibu.getaway.filter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @ClassName : GlobalExceptionConfiguration
 * @Description : 网关通用异常处理器，抛出的异常会在这里捕获并处理返回
 * @Author : wzq
 * @Date: 2023-02-07 16:49
 */
@Order(-1)
public class GlobalExceptionConfiguration implements ErrorWebExceptionHandler {

    String errorJson = "";

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ServerHttpResponse response = exchange.getResponse();
        if (response.isCommitted()) {
            return Mono.error(ex);
        }
        ex.printStackTrace();
        // header set
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        if (ex instanceof ResponseStatusException) {
            errorJson = "{\"code\":\"404\",\"message\":\"路由失败\",\"success\":false}";
        }else if (ex instanceof ExpiredJwtException){
            errorJson = "{\"code\":\"401\",\"message\":\"认证已过期\",\"success\":false}";
        }else if (ex instanceof SignatureException){
            errorJson = "{\"code\":\"401\",\"message\":\"认证无效\",\"success\":false}";
        }else {
            errorJson ="{\"code\":\"500\",\"message\":\"网关出现异常\",\"success\":false}";
        }

        return response
                .writeWith(Mono.fromSupplier(() -> {
                    DataBufferFactory bufferFactory = response.bufferFactory();
                        return bufferFactory.wrap(errorJson.getBytes());
                }));
    }
}
