package com.yibu.admin.contorller;

import com.alibaba.fastjson.JSON;
import com.wang.bean.UserInfo;
import com.wang.util.JwtUtils;
import com.yibu.web.dto.HttpResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName : AdminUserContorller
 * @Description :
 * @Author : wzq
 * @Date: 2023-02-02 16:11
 */
@RestController
public class AdminUserController {

    @GetMapping("userinfo")
    public HttpResult<String> test(HttpServletRequest request){
        String authorization = request.getHeader("Authorization");
        String userInfo = JwtUtils.getUserInfoFromToken(StringUtils.substringAfter(authorization, JwtUtils.TOKEN_PREFIX));
        System.out.println(userInfo);
        UserInfo info = JSON.parseObject(userInfo, UserInfo.class);
        return HttpResult.success(info.getUsername());
    }
}
