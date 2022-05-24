package com.aashish.daggerloginapp.di;

import com.aashish.daggerloginapp.di.auth.AuthModule;
import com.aashish.daggerloginapp.di.auth.AuthViewModelsModule;
import com.aashish.daggerloginapp.di.main.MainFragmentBuildersModule;
import com.aashish.daggerloginapp.di.main.MainViewModelsModule;
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

    @ContributesAndroidInjector (
            modules = {MainViewModelsModule.class, MainFragmentBuildersModule.class}
    )
    abstract MainActivity contributesMainActivity();
}
