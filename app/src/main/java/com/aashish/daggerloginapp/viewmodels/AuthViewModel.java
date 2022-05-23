package com.aashish.daggerloginapp.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.aashish.daggerloginapp.SessionManager;
import com.aashish.daggerloginapp.models.AuthResource;
import com.aashish.daggerloginapp.models.User;
import com.aashish.daggerloginapp.network.auth.AuthApi;

import javax.inject.Inject;

import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {
    private static final String TAG = "AuthViewModel";
    private final AuthApi authApi;
    private final SessionManager mSessionManager;

    @Inject
    public AuthViewModel(AuthApi authApi, SessionManager sessionManager) {
        Log.d(TAG, "AuthViewModel: constructor initialized");
        this.authApi = authApi;
        this.mSessionManager = sessionManager;
    }

    public void attemptLoginByUserId(String userId) {
        LiveData<AuthResource<User>> _authUser = queryUserById(userId);
        mSessionManager.authenticateWithId(_authUser);
    }

    private LiveData<AuthResource<User>> queryUserById(String userId) {
        return LiveDataReactiveStreams.fromPublisher(
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
    }

    public MediatorLiveData<AuthResource<User>> getAuthState() {
        return mSessionManager.getAuthUser();
    }
}
