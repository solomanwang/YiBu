package com.yibu.getaway.filter;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @ClassName : DefaultAuthenticationEntryPoint
 * @Description : 自定义未认证的处理
 * @Author : wzq
 * @Date: 2023-02-01 18:41
 */
@Component
public class DefaultAuthenticationEntryPoint implements ServerAuthenticationEntryPoint {

    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException e) {

        return Mono.defer(()->Mono.just(exchange.getResponse()))
                .flatMap(response ->{
                    response.setStatusCode(HttpStatus.UNAUTHORIZED);
                    response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
                    DataBufferFactory factory = response.bufferFactory();
                    DataBuffer wrap = factory.wrap("{\"code\":\"401\",\"message\":\"未认证\",\"success\":false}".getBytes());
                    return response.writeWith(Mono.just(wrap));
                });
    }
}
