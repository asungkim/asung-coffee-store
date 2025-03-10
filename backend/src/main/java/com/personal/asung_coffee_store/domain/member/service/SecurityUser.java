package com.personal.asung_coffee_store.domain.member.service;

import com.personal.asung_coffee_store.domain.member.enums.Role;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class SecurityUser extends User {
    private final Long id;
    private final Role role;
    private final String nickname;

    public SecurityUser(Long id, String username, String password, String nickname, Role role, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = id;
        this.role = role;
        this.nickname = nickname;
    }


}
