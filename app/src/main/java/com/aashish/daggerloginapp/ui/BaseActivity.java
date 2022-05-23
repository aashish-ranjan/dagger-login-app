package com.aashish.daggerloginapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.aashish.daggerloginapp.SessionManager;
import com.aashish.daggerloginapp.models.AuthResource;
import com.aashish.daggerloginapp.models.User;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class BaseActivity extends DaggerAppCompatActivity {
    private static final String TAG = "BaseActivity";

    @Inject
    SessionManager mSessionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        subscribeObservers();
    }

    private void subscribeObservers() {
        mSessionManager.getAuthUser().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> user) {
                switch (user.status) {
                    case AUTHENTICATED: {
                        Log.d(TAG, "onChanged: logged in with user id: " + user.data.getId());
                        break;
                    }
                    case ERROR: {
                        Toast.makeText(BaseActivity.this, "Invalid user id\n Error: " + user.message, Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case LOADING: {
                        Log.d(TAG, "onChanged: LOADING..");
                        break;
                    }
                    case NOT_AUTHENTICATED: {
                        Log.d(TAG, "onChanged: NOT AUTHENTICATED.. Navigating to login screen");
                        navigateToLoginScreen();
                        break;
                    }
                }
            }
        });
    }

    private void navigateToLoginScreen() {
        Intent intent = new Intent(BaseActivity.this, AuthActivity.class);
        startActivity(intent);
        finish();
    }
}
