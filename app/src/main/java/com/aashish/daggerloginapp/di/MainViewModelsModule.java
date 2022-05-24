package com.aashish.daggerloginapp.di;

import androidx.lifecycle.ViewModel;

import com.aashish.daggerloginapp.viewmodels.ProfileViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel.class)
    abstract ViewModel bindsProfileViewModel(ProfileViewModel profileViewModel);
}
