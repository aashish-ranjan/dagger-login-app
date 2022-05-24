package com.aashish.daggerloginapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.aashish.daggerloginapp.models.AuthResource;
import com.aashish.daggerloginapp.models.User;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SessionManager {
    private final MediatorLiveData<AuthResource<User>> _cachedUser = new MediatorLiveData<>();

    @Inject
    public SessionManager() {
    }

    public void authenticateWithId(final LiveData<AuthResource<User>> source) {
        _cachedUser.setValue(AuthResource.loading((User)null));
        _cachedUser.addSource(source, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> user) {
                _cachedUser.setValue(user);
                _cachedUser.removeSource(source);
            }
        });
    }

    public void logout() {
        _cachedUser.setValue(AuthResource.logout());
    }

    public MediatorLiveData<AuthResource<User>> getAuthenticatedUser() {
        return _cachedUser;
    }
}
