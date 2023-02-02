package com.yibu.getaway.config;

import com.yibu.getaway.filter.CustomReactiveAuthorizationManager;
import com.yibu.getaway.filter.CustomizeAccessDeniedHandler;
import com.yibu.getaway.filter.DefaultAuthenticationEntryPoint;
import com.yibu.getaway.filter.DefaultSecurityContextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * @ClassName : ResourceServerConfig
 * @Description : 资源管理器配置类，控制拦截放行请求，设置自定义的校验和返回
 * @Author : wzq
 * @Date: 2023-01-31 16:34
 */
@Configuration
@EnableWebFluxSecurity
public class ResourceServerConfig {

    @Autowired
    private DefaultAuthenticationEntryPoint defaultAuthenticationEntryPoint;
    @Autowired
    private CustomReactiveAuthorizationManager authorizationManager;
    @Autowired
    private DefaultSecurityContextRepository securityContextRepository;
    @Autowired
    private CustomizeAccessDeniedHandler customizeAccessDeniedHandler;

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http){
        // 设置 存储认证授权的相关信息
        http.securityContextRepository(securityContextRepository);
        http.csrf().disable()
                .authorizeExchange(exchange -> exchange
                        // oauth 所有请求放行
                        .pathMatchers("/oauth/**").permitAll()
                        // 所有 OPTIONS 请求放行
                        .pathMatchers(HttpMethod.OPTIONS).permitAll()
                        // TODO 统一配置放行的url
//                        .pathMatchers(ignoreUrl).permitAll()
                        // 自定义授权管理器
                        .anyExchange().access( authorizationManager)
                        .and()
                        .exceptionHandling()
                        //鉴权失败自定义返回
                        .accessDeniedHandler(customizeAccessDeniedHandler)
                        //自定义未认证的处理
                        .authenticationEntryPoint(defaultAuthenticationEntryPoint));
        return http.build();
    }

}
