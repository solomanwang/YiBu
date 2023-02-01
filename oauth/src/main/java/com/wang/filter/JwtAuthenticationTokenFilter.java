package com.wang.filter;

import com.alibaba.fastjson.JSON;
import com.wang.pojo.YibuAccount;
import com.wang.service.AuthService;
import com.yibu.jwt.util.JwtUtils;
import com.wang.service.AuthService;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName : JwtAuthenticationTokenFilter
 * @Description : JWT登录授权过滤器
 * @Author : wzq
 * @Date: 2023-01-12 10:01
 */
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private AuthService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取token
        String token = req.getHeader("Authorization");
        if (StringUtils.isBlank(token)) {
            filterChain.doFilter(req,response);
            return;
        }
        // 解析token
        Claims claims = JwtUtils.parseJWT(token);
        if (claims!=null){
            YibuAccount account = JSON.parseObject(claims.getSubject(), YibuAccount.class);

            YibuAccount yibuAccount = userDetailsService.loadUserByUsername(account.getUsername());
            // 这里必须用3个参数的构造函数，（Object principal, Object credentials）会因为 credentials 报异常 BadCredentialsException: Bad credentials
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(account, null,yibuAccount.getAuthorities());
           // 从HttpServletRequest对象构建详细信息对象，并设置详情
            authenticationToken
                    .setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
            // 将给定的SecurityContext与当前执行线程相关联
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(req,response);
    }
}
