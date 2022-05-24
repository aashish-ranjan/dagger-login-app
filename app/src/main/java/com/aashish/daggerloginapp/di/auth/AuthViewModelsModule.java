package com.aashish.daggerloginapp.di.auth;

import androidx.lifecycle.ViewModel;

import com.aashish.daggerloginapp.di.ViewModelKey;
import com.aashish.daggerloginapp.viewmodels.AuthViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AuthViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    public abstract ViewModel bindsAuthViewModel(AuthViewModel viewModel);
}
