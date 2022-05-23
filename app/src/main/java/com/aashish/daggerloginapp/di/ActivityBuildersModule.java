package com.aashish.daggerloginapp.di;

import com.aashish.daggerloginapp.ui.AuthActivity;
import com.aashish.daggerloginapp.ui.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector (
            modules = {AuthViewModelsModule.class, AuthModule.class}
    )
    abstract AuthActivity contributesAuthActivity();

    @ContributesAndroidInjector
    abstract MainActivity contributesMainActivity();
}
