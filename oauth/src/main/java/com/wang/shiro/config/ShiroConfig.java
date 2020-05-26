package com.wang.shiro.config;

import com.wang.session.CustomSessionManager;
import com.wang.shiro.realm.BaseRealm;
import com.wang.shiro.realm.UserRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * @author wzq
 * @date 2020/5/26
 * @DESC
 */
@Configuration
public class ShiroConfig {

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;

    // 创建realm
    @Bean
    public BaseRealm getRealm(){return new UserRealm(); }

    /**
     * 创建安全管理器
     */
    @Bean
    public SessionsSecurityManager getSecuritManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(getRealm());
        // 将自定义的绘画管理器注册到安全管理器中
        securityManager.setSessionManager(sessionManager());
        // 将自定义的redis缓存管理器注册到安全管理容器中
        securityManager.setCacheManager(cacheManager());
        return securityManager;
    }

    /**
     * shiro 权限过滤
     * */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        // 1,创建过滤工厂
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        // 2,设置安全管理器
        filterFactoryBean.setSecurityManager(securityManager);
        // 3,通用配置（跳转登录页面，未授权跳转页面）
        // 设置所有的过滤器 key：拦截的url地址，value:过滤器类型
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        // anon --匿名访问
        map.put("/auth/accredit","anon");
        map.put("/auth/registered","anon");
        // authc --认证之后访问
        map.put("/**","authc");
        // perms --具有某种权限（推荐使用注解配置授权）
        filterFactoryBean.setFilterChainDefinitionMap(map);
        return filterFactoryBean;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    /**
     *  redisManager
     * */
    public RedisManager redisManager(){
        return new RedisManager();
    }

    /**
     * sessionDao
     * */
    public RedisSessionDAO redisSessionDAO(){
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    /**
     * 会画管理器
     * */
    public DefaultWebSessionManager sessionManager(){
        CustomSessionManager sessionManager = new CustomSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }

    /**
     * 缓存管理
     * */
    public RedisCacheManager cacheManager(){
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }
}
