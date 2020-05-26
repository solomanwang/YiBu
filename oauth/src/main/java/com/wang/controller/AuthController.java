package com.wang.controller;

import com.wang.bean.ProfileResult;
import com.wang.config.JwtProperties;
import com.wang.exception.ExceptionKey;
import com.wang.feign.UserFeign;
import com.wang.service.AuthService;
import com.yibu.user.pojo.User;
import com.yibu.web.dto.HttpResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
    @Resource
    private UserFeign userFeign;
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
        // 登录校验 jwt
//        String token = this.authService.accredit(mobile, pwd);
//        if (StringUtils.isNotBlank(token)){
//            CookieUtils.setCookie(request,response,jwtProperties.getCookieName(),token,jwtProperties.getExpire()*60);
//            return HttpResult.success();
//        }
//        throw new WErrorException(ExceptionKey.WRONG_PASSWORD);
        try {
            // shiro 登录
            //  1,构造登录令牌
            pwd = new Md5Hash(pwd, mobile, 3).toString();
            UsernamePasswordToken passwordToken = new UsernamePasswordToken(mobile,pwd);
            //  2,获取subject，调用login方法，进去realm完成认证
            Subject subject = SecurityUtils.getSubject();
            subject.login(passwordToken);
            //  3，获取sessionId
            String sessionId = subject.getSession().getId().toString();
            //  4，构造返回结果
            return HttpResult.success(sessionId);
        }catch (Exception e){
            return HttpResult.error(ExceptionKey.AUTHENTICATION_FAILED);
        }
    }

    @PostMapping("registered")
    public HttpResult<User> registered(@RequestBody @Valid User user){

        String pwd = new Md5Hash(user.getMobile(), user.getPassword(), 3).toString();
        user.setPassword(pwd);
        return userFeign.registered(user);
    }

    @GetMapping("profile")
    public HttpResult<ProfileResult> getProfileResult(){
        Subject subject = SecurityUtils.getSubject();
        PrincipalCollection principals = subject.getPrincipals();
        ProfileResult result = (ProfileResult) principals.getPrimaryPrincipal();
        return HttpResult.success(result);
    }

}
