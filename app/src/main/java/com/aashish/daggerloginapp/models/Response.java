package com.aashish.daggerloginapp.models;

import androidx.annotation.Nullable;

public class Response<T> {
    public T data;
    public String message;
    public Status status;

    public Response(T data, String message, Status status) {
        this.data = data;
        this.message = message;
        this.status = status;
    }

    public static <T> Response<T> success(T data) {
        return new Response<>(data, null, Status.SUCCESS);
    }

    public static <T> Response<T> error(@Nullable T data, String message) {
        return new Response<>(data, message, Status.ERROR);
    }

    public static <T> Response<T> loading(@Nullable T data) {
        return new Response<>(data, null, Status.LOADING);
    }

    public enum Status { SUCCESS, LOADING, ERROR };
}
