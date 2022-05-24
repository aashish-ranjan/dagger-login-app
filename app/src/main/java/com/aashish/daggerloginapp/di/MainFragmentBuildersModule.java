package com.aashish.daggerloginapp.di;

import com.aashish.daggerloginapp.ui.profile.ProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract ProfileFragment contributesProfileFragment();
}
