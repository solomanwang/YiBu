package com.wang.service;

import cn.hutool.core.util.ObjectUtil;
import com.wang.config.JwtProperties;
import com.wang.feign.UserFeign;
import com.wang.util.JwtUtils;
import com.yibu.user.pojo.User;
import com.yibu.web.dto.HttpResult;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wzq
 * @date 2020/5/21
 * @DESC
 */
@Service
public class AuthService {

    @Autowired
    private UserFeign userFeign;
    @Autowired
    private JwtProperties jwtProperties;

    public String accredit(String mobile, String pwd) throws Exception {
            // 查询用户
            HttpResult<User> result = userFeign.login(mobile, pwd);
            if (ObjectUtil.isNull(result.getResult())){
                return null;
            }
            User user = result.getResult();
            Map<String,Object> map = new HashMap<>();
            map.put("id",user.getId());
            map.put("username",user.getUsername());
            return JwtUtils.generateToken(map, jwtProperties.getPrivateKey(), jwtProperties.getExpire());
    }
}
