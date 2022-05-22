package com.aashish.daggerloginapp.di;

import com.aashish.daggerloginapp.ui.AuthActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector (
            modules = {AuthViewModelsModule.class, AuthModule.class}
    )
    abstract AuthActivity contributesAuthActivity();
}
