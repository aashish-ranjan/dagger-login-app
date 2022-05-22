package com.aashish.daggerloginapp.viewmodels;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.aashish.daggerloginapp.network.auth.AuthApi;

import javax.inject.Inject;

public class AuthViewModel extends ViewModel {
    private static final String TAG = "AuthViewModel";

    @Inject
    public AuthViewModel(AuthApi authApi) {
        Log.d(TAG, "AuthViewModel: constructor initialized");

        if (authApi == null) {
            Log.d(TAG, "AuthViewModel: Auth api is NULL");
        } else {
            Log.d(TAG, "AuthViewModel: Auth api is NOT NULL");
        }
    }
}
