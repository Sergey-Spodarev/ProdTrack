package com.example.ProdTrack.security;

import com.example.ProdTrack.model.Users;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;

@Getter
public class CustomUserDetails implements UserDetails, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final String userName;
    private final String password;

    //эти все поля нужны для наследования от UserDetails
    private final Collection<? extends GrantedAuthority> authorities;//для работы с полномочиями
    private final boolean enabled;//Активность аккаунта
    private final boolean accountNonExpired;//Не истёк ли срок аккаунта
    private final boolean credentialsNonExpired;//Не истёк ли срок пароля
    private final boolean accountNonLocked;//Заблокирован ли пользователь

    CustomUserDetails(Users user) {
        this.userName = user.getUsername();
        this.password = user.getPassword();
        this.authorities = AuthorityUtils.createAuthorityList(user.getRole());//AuthorityUtils для работы с полномочиями в Spring Security

        this.enabled = true;
        this.accountNonExpired = true;
        this.credentialsNonExpired = true;
        this.accountNonLocked = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){//Возвращает список прав/ролей пользователя
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
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
