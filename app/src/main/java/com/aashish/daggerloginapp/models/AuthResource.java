package com.aashish.daggerloginapp.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AuthResource<T> {
    @Nullable public final T data;
    @Nullable public final String message;
    @NonNull public final Status status;

    public AuthResource(@Nullable T data, @Nullable String message, @NonNull Status status) {
        this.data = data;
        this.message = message;
        this.status = status;
    }

    public static <T> AuthResource<T> success(@Nullable T data) {
        return new AuthResource<>(data, null, Status.AUTHENTICATED);
    }

    public static <T> AuthResource<T> error(@Nullable T data, @Nullable String message) {
        return new AuthResource<>(data, message, Status.ERROR);
    }

    public static <T> AuthResource<T> loading(@Nullable T data) {
        return new AuthResource<>(data, null, Status.LOADING);
    }

    public static <T> AuthResource<T> logout() {
        return new AuthResource<>(null, null, Status.NOT_AUTHENTICATED);
    }

    public enum Status {AUTHENTICATED, ERROR, LOADING, NOT_AUTHENTICATED};
}
