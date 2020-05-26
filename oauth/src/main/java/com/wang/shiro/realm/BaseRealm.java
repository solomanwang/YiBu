package com.wang.shiro.realm;

import com.wang.bean.ProfileResult;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Map;

/**
 * @author wzq
 * @date 2020/5/26
 * @DESC 公共realm：获取安全数据，构造权限信息
 */
public class BaseRealm extends AuthorizingRealm {



    public void setName(String name){super.setName("BaseRealm");}

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 1, 获取安全数据
        ProfileResult profile = (ProfileResult) principals.getPrimaryPrincipal();
        // 2, 获取权限信息
        Map<String, Object> roles = profile.getRoles();
        // 3, 构造权限数据，返回值
//        new SimpleAuthorizationInfo()
        return null;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        return null;
    }
}
