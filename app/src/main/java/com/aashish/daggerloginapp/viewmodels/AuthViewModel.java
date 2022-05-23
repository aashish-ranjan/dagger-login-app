package com.aashish.daggerloginapp.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.aashish.daggerloginapp.models.User;
import com.aashish.daggerloginapp.network.auth.AuthApi;

import javax.inject.Inject;

import io.reactivex.rxjava3.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {
    private static final String TAG = "AuthViewModel";
    private final AuthApi authApi;

    private final MediatorLiveData<User> _authUser = new MediatorLiveData<>();

    @Inject
    public AuthViewModel(AuthApi authApi) {
        Log.d(TAG, "AuthViewModel: constructor initialized");
        this.authApi = authApi;
    }

    public void getUserInfo(String userId) {
        final LiveData<User> source = LiveDataReactiveStreams.fromPublisher(
                authApi.getUsers(userId)
                        .subscribeOn(Schedulers.io())
        );

        _authUser.addSource(source, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                _authUser.setValue(user);
                _authUser.removeSource(source);
            }
        });
    }

    public MediatorLiveData<User> getAuthUserLiveData() {
        return _authUser;
    }
}
