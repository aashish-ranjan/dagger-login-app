package com.aashish.daggerloginapp.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.aashish.daggerloginapp.models.AuthResource;
import com.aashish.daggerloginapp.models.User;
import com.aashish.daggerloginapp.network.auth.AuthApi;

import javax.inject.Inject;

import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {
    private static final String TAG = "AuthViewModel";
    private final AuthApi authApi;

    private final MediatorLiveData<AuthResource<User>> _authUser = new MediatorLiveData<>();

    @Inject
    public AuthViewModel(AuthApi authApi) {
        Log.d(TAG, "AuthViewModel: constructor initialized");
        this.authApi = authApi;
    }

    public void getUserInfo(String userId) {
        _authUser.setValue(AuthResource.loading((User)null));
        final LiveData<AuthResource<User>> source = LiveDataReactiveStreams.fromPublisher(
                authApi.getUsers(userId)
                        .onErrorReturn(new Function<Throwable, User>() {
                            @Override
                            public User apply(Throwable throwable) throws Throwable {
                                User errorUser = new User();
                                errorUser.setId(-1);
                                return errorUser;
                            }
                        })
                        .map(new Function<User, AuthResource<User>>() {
                            @Override
                            public AuthResource<User> apply(User user) throws Throwable {
                                if (user.getId() == -1) {
                                    return AuthResource.error((User)null, "Could not authenticate");
                                } else {
                                    return AuthResource.success(user);
                                }
                            }
                        })
                        .subscribeOn(Schedulers.io())
        );

        _authUser.addSource(source, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> user) {
                _authUser.setValue(user);
                _authUser.removeSource(source);
            }
        });
    }

    public MediatorLiveData<AuthResource<User>> getAuthUserLiveData() {
        return _authUser;
    }
}
