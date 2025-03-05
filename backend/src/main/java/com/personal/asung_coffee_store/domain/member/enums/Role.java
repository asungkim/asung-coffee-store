package com.personal.asung_coffee_store.domain.member.enums;

public enum Role {
    ROLE_USER,
    ROLE_ADMIN;

    public String getValue() {
        return name();
    }
}
