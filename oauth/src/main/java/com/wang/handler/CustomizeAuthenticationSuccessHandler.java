package com.wang.handler;

import com.alibaba.fastjson.JSON;
import com.yibu.jwt.util.JwtUtils;
import com.wang.pojo.YibuAccount;
import com.wang.result.ResultRes;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName : CustomizeAuthenticationSuccessHandler
 * @Description : 认证成功处理
 * @Author : wzq
 * @Date: 2023-01-12 15:21
 */
@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        YibuAccount YibuAccount = (YibuAccount) authentication.getPrincipal();
        String jwtToken = JwtUtils.createJWT(YibuAccount.getId().toString(), JSON.toJSONString(YibuAccount), JwtUtils.EXPIRE);
        //此处还可以进行一些处理，比如登录成功之后可能需要返回给前台当前用户有哪些菜单权限，
        //进而前台动态的控制菜单的显示等，具体根据自己的业务需求进行扩展
        Map<String,String> results = new HashMap<>();
        results.put("token",jwtToken);
        //处理编码方式，防止中文乱码的情况
        response.setContentType("text/json;charset=utf-8");
        // 把Json数据放入HttpServletResponse中返回给前台
        response.getWriter().write(JSON.toJSONString(ResultRes.success(results)));
    }
}
