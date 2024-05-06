package com.hurtowania.hurtowniaspozywcza.auth;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hurtowania.hurtowniaspozywcza.AppUser.AppUser;

import lombok.Getter;

import java.util.*;

public class AppUserDetails implements UserDetails{
    @Getter
    private long id;
    @Getter
    private String userName;
    @JsonIgnore
    private String password;
    private final Collection<? extends GrantedAuthority> authorities;

    public AppUserDetails(long id, String username, String password,
                             Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.userName = username;
        this.password = password;
        this.authorities = authorities;
    }

    public static AppUserDetails builder(AppUser user){
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(user.getType().toString()));

        return new AppUserDetails(
            user.getId(),
            user.getUserName(),
            user.getPassword(),
            authorities
        );

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
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

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;

        if(obj == null || getClass() != obj.getClass())
            return false;

        AppUser user = (AppUser) obj;
        return Objects.equals(id, user.getId());
    }
}
