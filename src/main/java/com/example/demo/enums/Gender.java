package com.example.demo.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Gender implements GrantedAuthority {
    М, Ж;

    @Override
    public String getAuthority() {
        return name();
    }
}
