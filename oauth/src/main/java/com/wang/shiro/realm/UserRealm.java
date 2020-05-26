package com.wang.shiro.realm;

import com.wang.bean.ProfileResult;
import com.wang.feign.UserFeign;
import com.yibu.user.pojo.User;
import org.apache.shiro.authc.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wzq
 * @date 2020/5/26
 * @DESC 用户realm
 */
public class UserRealm extends BaseRealm {

    @Autowired
    private UserFeign userFeign;

    /**
     * 重写认证方法
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 1,获取用户手机号和密码
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String pwd = String.valueOf(usernamePasswordToken.getPassword());
        // 2,根据手机号查询用户
        User user = userFeign.queryUserByMobile(usernamePasswordToken.getUsername()).getResult();
        // 3,判断用户是否存在，用户名密码是否输入一致
        if (user != null && pwd.equals(user.getPassword())){
            // 构建安全数据
            ProfileResult result = null;
            // 4,构造安全数据并返回（用户基本数据，权限信息）
            return new SimpleAuthenticationInfo(result,user.getPassword(),this.getName());
        }
        // 返回null 会抛出异常
        return null;
    }
}
