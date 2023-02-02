package com.yibu.getaway.filter;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import reactor.core.publisher.Mono;

import java.util.*;

/**
 * @ClassName : CustomReactiveAuthorizationManager
 * @Description : 自定义授权管理器，判断用户是否有权限访问
 * @Author : wzq
 * @Date: 2023-02-02 08:35
 */
@Slf4j
@Component
public class CustomReactiveAuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    public static Map<String, List<String>> configAttributeMap = null;
    {
        configAttributeMap = new HashMap<>();
        configAttributeMap.put("/admin/**", CollUtil.toList("ROLE_ADMIN"));
        configAttributeMap.put("/user/**", CollUtil.toList("ROLE_USER","ROLE_VIP","ROLE_ADMIN"));
        configAttributeMap.put("/vip/**", CollUtil.toList("ROLE_VIP","ROLE_ADMIN"));
    }

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {

        ServerHttpRequest request = authorizationContext.getExchange().getRequest();
        String path = request.getURI().getPath();
        AntPathMatcher matcher = new AntPathMatcher();
        // 1. 对应跨域的预检请求直接放行
        if (request.getMethod() == HttpMethod.OPTIONS) {
            return Mono.just(new AuthorizationDecision(true));
        }

        // 2. token为空拒绝访问
        String token = request.getHeaders().getFirst("Authorization");
        if (StrUtil.isBlank(token)) {
            return Mono.just(new AuthorizationDecision(false));
        }
        // 3 获取资源对应角色信息
        List<String> authorities = new ArrayList<>();
        Iterator<String> iterator = configAttributeMap.keySet().iterator();
        //获取访问该路径所需资源,匹配资源需要的角色
        while (iterator.hasNext()) {
            String pattern = iterator.next();
            if (matcher.match(pattern, path)) {
                authorities.addAll(configAttributeMap.get(pattern));
            }
        }
        Mono<AuthorizationDecision> authorizationDecisionMono = mono.filter(Authentication::isAuthenticated)
                .flatMapIterable(Authentication::getAuthorities)
                .map(GrantedAuthority::getAuthority)
                .any(role -> {
                    log.debug("角色：", role);
                    return authorities.contains(role);
                })
                .map(AuthorizationDecision::new)
                .defaultIfEmpty(new AuthorizationDecision(false));
        return authorizationDecisionMono;
    }

}
