package com.yibu.getaway.filter;

import com.alibaba.fastjson.JSON;
import com.yibu.getaway.pojo.YibuAccount;
import com.yibu.jwt.util.JwtUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @ClassName : TokenAuthenticationManager
 * @Description : 自定义认证处理
 * @Author : wzq
 * @Date: 2023-02-02 14:18
 */
@Component
@Primary
public class TokenAuthenticationManager  implements ReactiveAuthenticationManager {

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String token = authentication.getPrincipal().toString();
        Mono<Authentication> tokenMono = Mono.just(authentication)
                .map(auth -> JwtUtils.parseJWT(token))
                .map(claims -> {
                    YibuAccount obj = JSON.parseObject(claims.getSubject(), YibuAccount.class);
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
