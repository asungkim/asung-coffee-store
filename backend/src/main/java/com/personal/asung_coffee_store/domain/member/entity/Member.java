package com.personal.asung_coffee_store.domain.member.entity;

import com.personal.asung_coffee_store.domain.member.enums.Role;
import com.personal.asung_coffee_store.global.entity.BaseTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class Member extends BaseTime {

    @Column(length = 20, unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(length = 20)
    private String phoneNumber;

    private String address;

    @Column(length = 10)
    private String zipcode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
}
