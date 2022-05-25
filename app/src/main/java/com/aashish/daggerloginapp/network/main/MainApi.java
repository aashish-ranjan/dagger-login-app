package com.aashish.daggerloginapp.network.main;

import com.aashish.daggerloginapp.models.Post;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MainApi {
    @GET("posts")
    Flowable<List<Post>> getPostsByUserId(@Query("userId") int userId);
}
