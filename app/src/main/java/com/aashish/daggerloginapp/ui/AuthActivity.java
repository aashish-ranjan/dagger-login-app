package com.aashish.daggerloginapp.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.aashish.daggerloginapp.R;
import com.aashish.daggerloginapp.models.AuthResource;
import com.aashish.daggerloginapp.models.User;
import com.aashish.daggerloginapp.viewmodels.AuthViewModel;
import com.aashish.daggerloginapp.viewmodels.ViewModelProviderFactory;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity implements View.OnClickListener {
    private static final String TAG = "AuthActivity";

    private EditText mUserIdEditText;
    private ProgressBar mProgressBar;

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    @Inject
    Drawable logo;

    @Inject
    RequestOptions requestOptions;

    @Inject
    RequestManager requestManager;

    private AuthViewModel mAuthViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        mUserIdEditText = findViewById(R.id.user_id_input);
        mProgressBar = findViewById(R.id.progress_bar);
        findViewById(R.id.login_button).setOnClickListener(this);

        mAuthViewModel = new ViewModelProvider(this, viewModelProviderFactory).get(AuthViewModel.class);

        requestManager
                .setDefaultRequestOptions(requestOptions)
                .load(logo)
                .into((ImageView) findViewById(R.id.login_logo));

        subscribeObservers();
    }

    private void subscribeObservers() {
        mAuthViewModel.getAuthState().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> user) {
                switch (user.status) {
                    case SUCCESS: {
                        showProgressBar(false);
                        Log.d(TAG, "onChanged: SUCCESS. User email is " + user.data.getEmail());
                        break;
                    }
                    case ERROR: {
                        showProgressBar(false);
                        Toast.makeText(AuthActivity.this, "Invalid user id\n Error: " + user.message, Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case LOADING: {
                        showProgressBar(true);
                        break;
                    }
                }
            }
        });
    }

    private void showProgressBar(Boolean shouldShowLoader) {
        if (shouldShowLoader) {
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            mProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_button: {
                attemptLogin(mUserIdEditText.getText().toString());
                break;
            }
        }
    }

    private void attemptLogin(String userId) {
         if (!TextUtils.isEmpty(userId)) {
             mAuthViewModel.attemptLoginByUserId(userId);
         } else {
             Toast.makeText(AuthActivity.this, "Enter a valid user id", Toast.LENGTH_SHORT).show();
         }
    }
}