package com.wang.service;

import cn.hutool.core.util.ObjectUtil;
import com.wang.config.JwtProperties;
import com.wang.feign.UserFeign;
import com.wang.pojo.YibuAccount;
import com.wang.util.JwtUtils;
import com.yibu.user.pojo.User;
import com.yibu.web.dto.HttpResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.UserDeniedAuthorizationException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wzq
 * @date 2020/5/21
 * @DESC
 */
@Service
public class AuthService implements UserDetailsService {

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

    @Override
    public YibuAccount loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isBlank(username)){
            throw new UsernameNotFoundException("用户名不能为空");
        }
        HttpResult<User> result = userFeign.queryUserByMobile(username);
        if (null == result ) throw new UsernameNotFoundException("用户不存在");
        if (!result.isSuccess() ) throw new UserDeniedAuthorizationException("查询用户出现错误");
        //  查询用户角色
        HttpResult<List<String>> roleByUid = userFeign.selectedRoleByUid(result.getResult());
        if (null == roleByUid || !roleByUid.isSuccess() ) throw new UserDeniedAuthorizationException("查询用户权限失败");
        YibuAccount account = new YibuAccount(result.getResult());
        account.setRoles(roleByUid.getResult());
//        account.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(StringUtils.join(roleByUid.getResult(),",")));
        return account;
    }
}
