package com.yibu.getaway.config;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTParser;
import com.nimbusds.jwt.PlainJWT;
import com.nimbusds.jwt.SignedJWT;
import com.yibu.jwt.util.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.security.oauth2.jwt.BadJwtException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import reactor.core.publisher.Mono;

/**
 * @ClassName : MyJwtDecoder
 * @Description : 自定义jwt解析
 * @Author : wzq
 * @Date: 2023-02-02 10:46
 */
public class MyJwtDecoder implements ReactiveJwtDecoder {
    @Override
    public Mono<Jwt> decode(String token) throws JwtException {
        Claims claims = JwtUtils.parseJWT(token);
        JWT parse = parse(token);
        return null;
    }
    private JWT parse(String token) {
        try {
            return JWTParser.parse(token);
        } catch (Exception var3) {
            throw new BadJwtException("An error occurred while attempting to decode the Jwt: " + var3.getMessage(), var3);
        }
    }
}
