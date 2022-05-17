package com.aashish.daggerloginapp.di;

import com.aashish.daggerloginapp.AuthActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract AuthActivity contributesAuthActivity();
}
