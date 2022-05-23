package com.aashish.daggerloginapp.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.aashish.daggerloginapp.R;
import com.aashish.daggerloginapp.models.User;
import com.aashish.daggerloginapp.viewmodels.AuthViewModel;
import com.aashish.daggerloginapp.viewmodels.ViewModelProviderFactory;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity implements View.OnClickListener {

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    @Inject
    Drawable logo;

    @Inject
    RequestOptions requestOptions;

    @Inject
    RequestManager requestManager;

    private AuthViewModel mAuthViewModel;

    private static final String TAG = "AuthActivity";
    private EditText mUserIdEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        mUserIdEditText = findViewById(R.id.user_id_input);
        findViewById(R.id.login_button).setOnClickListener(this);

        mAuthViewModel = new ViewModelProvider(this, viewModelProviderFactory).get(AuthViewModel.class);

        requestManager
                .setDefaultRequestOptions(requestOptions)
                .load(logo)
                .into((ImageView) findViewById(R.id.login_logo));

        subscribeObservers();
    }

    private void subscribeObservers() {
        mAuthViewModel.getAuthUserLiveData().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                Log.d(TAG, "onChanged: email is " + user.getEmail());
            }
        });
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
             mAuthViewModel.getUserInfo(userId);
         }
    }
}