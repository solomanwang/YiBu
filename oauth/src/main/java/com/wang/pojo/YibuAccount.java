package com.wang.pojo;

import com.yibu.user.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName : YibuAccount
 * @Description :
 * @Author : wzq
 * @Date: 2023-01-30 16:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class YibuAccount implements UserDetails {

    private Long id;
    private String username;
    private String password;
//    private List<? extends GrantedAuthority> authorities;
    private List<String> roles;

    public YibuAccount(User user){
        this.setId(user.getId());
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
    }

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
    public List<String> getRoles() {
        return roles;
    }

}

