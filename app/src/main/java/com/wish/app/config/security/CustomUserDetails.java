package com.wish.app.config.security;

import com.wish.domain.po.RolePO;
import com.wish.domain.po.UserPO;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by wangl on 2017/2/10.
 * 继承自User，包装了角色信息的User扩展类
 * 实现自spring-security提供的UserDetails，并实现接口
 * 用于映射一个用户的多权限查询结果集
 */
@Data
public class CustomUserDetails extends UserPO implements UserDetails {
    private Set<RolePO> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //定义权限集合
        List<GrantedAuthority> authorities = new ArrayList<>();
        //当前用户的角色信息集合
        Set<RolePO> roles = this.getRoles();
        //添加角色信息到权限集合
        for (RolePO role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleKey()));
        }
        return authorities;
    }

    @Override
    public String getUsername() {
        return getUserName();
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
