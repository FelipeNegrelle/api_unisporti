package com.unisporti.api_unisporti.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserContext {
    private static final ThreadLocal<UserContext> userContext = new ThreadLocal<>();

    private String cpf;
    private Integer userId;
    private char role;

    public static UserContext getCurrentUser() {
        return userContext.get();
    }

    public static void setCurrentUser(UserContext context) {
        userContext.set(context);
    }

    public static void clear() {
        userContext.remove();
    }
}