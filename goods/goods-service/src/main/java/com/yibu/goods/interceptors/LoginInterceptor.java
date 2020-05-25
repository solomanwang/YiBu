package com.yibu.goods.interceptors;

import com.wang.bean.UserInfo;
import com.wang.util.CookieUtils;
import com.wang.util.JwtUtils;
import com.yibu.goods.config.JwtProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author wzq
 * @date 2020/5/25
 * @DESC
 */
@EnableConfigurationProperties(JwtProperties.class)
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private JwtProperties properties;

    private static final ThreadLocal<UserInfo> THREAD_LOCAL = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserInfo userInfo = new UserInfo();

        // 获取cookie中的值
        String token = CookieUtils.getCookieValue(request, this.properties.getCookieName());
        // 解析token
        if (StringUtils.isNoneBlank(token)){
            Map<String, Object> infoFromToken = JwtUtils.getInfoFromToken(token, this.properties.getPublicKey());
            userInfo.setId(Long.valueOf(infoFromToken.get("id").toString()));
            userInfo.setUsername(infoFromToken.get("username").toString());
        }
        THREAD_LOCAL.set(userInfo);
        return super.preHandle(request, response, handler);
    }

    public static UserInfo getUserInfo(){
        return THREAD_LOCAL.get();
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        THREAD_LOCAL.remove();
    }
}
