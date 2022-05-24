package com.aashish.daggerloginapp.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.aashish.daggerloginapp.SessionManager;
import com.aashish.daggerloginapp.models.AuthResource;
import com.aashish.daggerloginapp.models.User;

import javax.inject.Inject;

public class ProfileViewModel extends ViewModel {
    private static final String TAG = "ProfileViewModel";
    private SessionManager mSessionManager;

    @Inject
    public ProfileViewModel(SessionManager sessionManager) {
        this.mSessionManager = sessionManager;
        Log.d(TAG, "ProfileViewModel is ready..");
    }

    public LiveData<AuthResource<User>> getUser() {
        return mSessionManager.getAuthenticatedUser();
    }
}
