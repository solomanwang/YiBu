package com.yibu.getaway.filter;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yibu.getaway.pojo.YibuAccount;
import com.yibu.jwt.util.JwtUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Collection;

/**
 * @ClassName : TokenAuthenticationManager
 * @Description : 认证处理
 * @Author : wzq
 * @Date: 2023-02-02 14:18
 */
@Component
@Primary
public class TokenAuthenticationManager  implements ReactiveAuthenticationManager {

    @Override
//    @SuppressWarnings("unchecked")
    public Mono<Authentication> authenticate(Authentication authentication) {
        String token = authentication.getPrincipal().toString();
        System.out.println("token:"+token);
        Mono<Authentication> tokenMono = Mono.just(authentication)
                .map(auth -> JwtUtils.parseJWT(token))
                .map(claims -> {
                    YibuAccount obj = JSON.parseObject(claims.getSubject(), YibuAccount.class);
                    System.out.println("角色："+obj.getAuthorities().toString());
                    return new UsernamePasswordAuthenticationToken(
                            obj,
                            null,
                            obj.getAuthorities()
//                            AuthorityUtils.commaSeparatedStringToAuthorityList(StringUtils.join(CollUtil.toList("ROLE_USER"),","))
                    );
                });
        return tokenMono;
    }
}
