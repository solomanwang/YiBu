package com.yibu.getaway.pojo;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @ClassName : YibuAccount
 * @Description :
 * @Author : wzq
 * @Date: 2023-01-30 16:40
 */
@Data
public class YibuAccount implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private List<String> roles;

    //获取用户角色权限，此处从数据库表Role中获取
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<>();
        List<String> roles = getRoles();
        if (roles != null) {
            for (String role : roles) {
                auths.add(new SimpleGrantedAuthority(role));
            }
        }
        return auths;
    }

    public List<String> getRoles() {
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
