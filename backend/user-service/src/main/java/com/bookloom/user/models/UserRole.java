package com.bookloom.user.models;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {
    User("ROLE_USER"), Admin("ROLE_ADMIN");

    private final String roleName;

}
