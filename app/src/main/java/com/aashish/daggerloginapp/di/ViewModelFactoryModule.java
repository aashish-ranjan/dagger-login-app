package com.aashish.daggerloginapp.di;

import androidx.lifecycle.ViewModelProvider;
import com.aashish.daggerloginapp.viewmodels.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindsViewModelFactory(ViewModelProviderFactory viewModelProviderFactory);
}
