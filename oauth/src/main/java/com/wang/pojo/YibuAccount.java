package com.wang.pojo;

import com.yibu.user.pojo.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
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
    private List<? extends GrantedAuthority> authorities;

    public YibuAccount(User user){
        this.setId(user.getId());
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
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
