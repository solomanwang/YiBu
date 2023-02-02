package com.wang.handler;

import com.alibaba.fastjson.JSON;
import com.wang.result.ResultRes;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName : CustomizeAuthenticationFailureHandler
 * @Description : 登录失败处理逻辑
 * @Author : wzq
 * @Date: 2023-01-17 15:40
 */
@Component
public class CustomizeAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        //返回json数据
//        e.printStackTrace();
        ResultRes result = null;
        if (e instanceof AccountExpiredException) {
            //账号过期
            result = ResultRes.fail("账号过期");
        } else if (e instanceof BadCredentialsException) {
            //密码错误
            result = ResultRes.fail("密码错误");
        } else if (e instanceof CredentialsExpiredException) {
            //密码过期
            result = ResultRes.fail("密码过期");
        } else if (e instanceof DisabledException) {
            //账号不可用
            result = ResultRes.fail("账号不可用");
        } else if (e instanceof LockedException) {
            //账号锁定
            result = ResultRes.fail("账号已被锁定");
        } else if (e instanceof InternalAuthenticationServiceException) {
            //用户不存在
            result = ResultRes.fail("用户不存在");
        }else{
            //其他错误
            result = ResultRes.fail("异常");
        }
        //处理编码方式，防止中文乱码的情况
        httpServletResponse.setContentType("text/json;charset=utf-8");
        //塞到HttpServletResponse中返回给前台
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }
}
