package com.aashish.daggerloginapp.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.lifecycle.ViewModelProvider;

import com.aashish.daggerloginapp.R;
import com.aashish.daggerloginapp.viewmodels.AuthViewModel;
import com.aashish.daggerloginapp.viewmodels.ViewModelProviderFactory;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        mAuthViewModel = new ViewModelProvider(this, viewModelProviderFactory).get(AuthViewModel.class);

        requestManager
                .setDefaultRequestOptions(requestOptions)
                .load(logo)
                .into((ImageView) findViewById(R.id.login_logo));
    }
}