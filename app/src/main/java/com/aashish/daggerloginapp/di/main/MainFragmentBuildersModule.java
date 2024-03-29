package com.aashish.daggerloginapp.di.main;

import com.aashish.daggerloginapp.ui.posts.PostsFragment;
import com.aashish.daggerloginapp.ui.profile.ProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract ProfileFragment contributesProfileFragment();

    @ContributesAndroidInjector
    abstract PostsFragment contributesPostsFragment();
}
