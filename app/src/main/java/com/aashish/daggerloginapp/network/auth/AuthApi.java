package com.aashish.daggerloginapp.network.auth;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AuthApi {

    @GET("users/{id}")
    Call<ResponseBody> getUsers(@Path("id") String userId);
}
