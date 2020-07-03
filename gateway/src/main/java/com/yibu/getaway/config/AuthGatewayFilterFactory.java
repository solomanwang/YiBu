package com.yibu.getaway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;

/**
 * @author wzq
 * @date 2020/5/21
 * @DESC
 */
//@Component
public class AuthGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {

    @Autowired
    private AuthGatewayFilter authGatewayFilter;

    @Override
    public GatewayFilter apply(Object config) {
        return authGatewayFilter;
    }
}
