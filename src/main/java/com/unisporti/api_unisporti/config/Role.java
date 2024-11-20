package com.unisporti.api_unisporti.config;

import lombok.Getter;

@Getter
public enum Role {
    ROLE_ADMIN('A', 4),
    ROLE_MANAGER('M', 3),
    ROLE_INSTRUCTOR('I', 2),
    ROLE_USER('U', 1);

    private final char role;
    private final int hierarchy;

    Role(char role, int hierarchy) {
        this.role = role;
        this.hierarchy = hierarchy;
    }

    public static int getHierarchyByRole(char role) {
        for (Role r : Role.values()) {
            if (r.getRole() == role) {
                return r.getHierarchy();
            }
        }
        return 0;
    }
}
