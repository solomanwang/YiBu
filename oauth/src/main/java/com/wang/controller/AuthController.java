package com.wang.controller;

import com.wang.config.JwtProperties;
import com.wang.exception.ExceptionKey;
import com.wang.exception.WErrorException;
import com.wang.service.AuthService;
import com.wang.util.CookieUtils;
import com.yibu.web.dto.HttpResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wzq
 * @date 2020/5/21
 * @DESC
 */
@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private JwtProperties jwtProperties;
    /**
     * @Author wzq
     * @Description: 登录授权
     * @Date 2020/5/21 16:19
     * @Param
     * @return
     **/
    @PostMapping("accredit")
    public HttpResult<Object> accredit(@RequestParam("mobile")String mobile, @RequestParam("pwd")String pwd,
                                       HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 登录校验
        String token = this.authService.accredit(mobile, pwd);
        if (StringUtils.isNotBlank(token)){
            CookieUtils.setCookie(request,response,jwtProperties.getCookieName(),token,jwtProperties.getExpire()*60);
            return HttpResult.success();
        }
        throw new WErrorException(ExceptionKey.WRONG_PASSWORD);
    }

}
