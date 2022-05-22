package com.aashish.daggerloginapp.network.auth;

import com.aashish.daggerloginapp.models.User;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AuthApi {

    @GET("users/{id}")
    Flowable<User> getUsers(@Path("id") String id);
}
