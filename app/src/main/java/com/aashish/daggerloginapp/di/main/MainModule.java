package com.aashish.daggerloginapp.di.main;

import com.aashish.daggerloginapp.network.main.MainApi;
import com.aashish.daggerloginapp.ui.posts.PostsRecyclerViewAdapter;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MainModule {

    @Provides
    static PostsRecyclerViewAdapter providesRecyclerViewAdapter() {
        return new PostsRecyclerViewAdapter();
    }

    @Provides
    static MainApi providesMainApi(Retrofit retrofit) {
        return retrofit.create(MainApi.class);
    }
}
