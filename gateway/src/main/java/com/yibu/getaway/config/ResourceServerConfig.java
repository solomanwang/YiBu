package com.yibu.getaway.config;

import com.yibu.getaway.filter.CustomReactiveAuthorizationManager;
import com.yibu.getaway.filter.CustomizeAccessDeniedHandler;
import com.yibu.getaway.filter.DefaultAuthenticationEntryPoint;
import com.yibu.getaway.filter.DefaultSecurityContextRepository;
import com.yibu.jwt.util.JwtUtils;
import org.bouncycastle.asn1.crmf.EncryptedKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.DelegatingReactiveAuthenticationManager;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.authorization.AuthenticatedReactiveAuthorizationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.LinkedList;

/**
 * @ClassName : ResourceServerConfig
 * @Description :
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
//        http.oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtAuthenticationConverter());
        http
//                .authenticationManager()
                        .securityContextRepository(securityContextRepository);
        http.csrf().disable()
                .authorizeExchange(exchange -> exchange
                        .pathMatchers("/oauth/**").permitAll()
                        .pathMatchers(HttpMethod.OPTIONS).permitAll()
                        .anyExchange().access( authorizationManager)
                        .and()
                        .exceptionHandling()
                        .accessDeniedHandler(customizeAccessDeniedHandler)
                        .authenticationEntryPoint(defaultAuthenticationEntryPoint));
        return http.build();
    }


    /**
     * 从jwt令牌中获取认证对象
     */
//    @Bean
    public Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> jwtAuthenticationConverter() {

        // 从jwt 中获取该令牌可以访问的权限
        JwtGrantedAuthoritiesConverter authoritiesConverter = new JwtGrantedAuthoritiesConverter();
        // 取消权限的前缀，默认会加上SCOPE_
        authoritiesConverter.setAuthorityPrefix("");
        // 从那个字段中获取权限
        authoritiesConverter.setAuthoritiesClaimName("authorities");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        // 获取 principal name
//        jwtAuthenticationConverter.("sub");
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(authoritiesConverter);

        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }

    /**
     * 解码jwt
     */
//    @Bean
    public ReactiveJwtDecoder jwtDecoder(){
        return NimbusReactiveJwtDecoder.withPublicKey(null)
                .signatureAlgorithm(SignatureAlgorithm.RS256)
                .build();
    }
}
