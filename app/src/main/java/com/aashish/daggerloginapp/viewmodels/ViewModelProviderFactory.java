package com.aashish.daggerloginapp.viewmodels;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


public class ViewModelProviderFactory implements ViewModelProvider.Factory {

    private static final String TAG = "ViewModelProviderFactor";

    private final Map<Class<? extends ViewModel>, Provider<ViewModel>> mViewModelProviderMap;

    @Inject
    public ViewModelProviderFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> viewModelProviderMap) {
        this.mViewModelProviderMap = viewModelProviderMap;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        Provider<? extends ViewModel> viewModelProvider = mViewModelProviderMap.get(modelClass);
        if (viewModelProvider == null) { // if the viewmodel has not been created

            // loop through the allowable keys (aka allowed classes with the @ViewModelKey)
            for (Map.Entry<Class<? extends ViewModel>, Provider<ViewModel>> entry : mViewModelProviderMap.entrySet()) {

                // if it's allowed, set the Provider<ViewModel>
                if (modelClass.isAssignableFrom(entry.getKey())) {
                    viewModelProvider = entry.getValue();
                    break;
                }
            }
        }

        // if this is not one of the allowed keys, throw exception
        if (viewModelProvider == null) {
            throw new IllegalArgumentException("unknown model class " + modelClass);
        }

        // return the Provider
        try {
            return (T) viewModelProvider.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
