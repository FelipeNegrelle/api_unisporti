package com.unisporti.api_unisporti.config;

public enum Roles {
    ROLE_ADMIN('A', 1),
    ROLE_MANAGER,
    ROLE_INSTRUCTOR,
    ROLE_USER;

    private final Character role;
    private final Byte hierarchy;

    public Role(Character role, Byte hierarchy) {
        this.role = role;
        this.hierarchy = hierarchy;
    }
}
