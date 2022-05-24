package com.aashish.daggerloginapp.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.aashish.daggerloginapp.R;
import com.aashish.daggerloginapp.models.AuthResource;
import com.aashish.daggerloginapp.models.User;
import com.aashish.daggerloginapp.viewmodels.ProfileViewModel;
import com.aashish.daggerloginapp.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class ProfileFragment extends DaggerFragment {

    @Inject
    public ViewModelProviderFactory mViewModelProviderFactory;

    private ProfileViewModel mProfileViewModel;
    private TextView mUserNameTextView, mEmailTextView, mWebsiteTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUserNameTextView = view.findViewById(R.id.tvUsername);
        mEmailTextView = view.findViewById(R.id.tvEmail);
        mWebsiteTextView = view.findViewById(R.id.tvWebsite);
        mProfileViewModel = new ViewModelProvider(this, mViewModelProviderFactory).get(ProfileViewModel.class);

        subscribeObservers();
    }

    private void subscribeObservers() {
        mProfileViewModel.getUser().observe(getViewLifecycleOwner(), new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if (userAuthResource != null) {
                    switch (userAuthResource.status) {
                        case AUTHENTICATED: {
                            setUserDetails(userAuthResource.data);
                            break;
                        }
                        case ERROR: {
                            setErrorDetails(userAuthResource.message);
                            break;
                        }
                    }
                }
            }
        });
    }

    private void setUserDetails(User data) {
        mUserNameTextView.setText(data.getUserName());
        mEmailTextView.setText(data.getEmail());
        mWebsiteTextView.setText(data.getWebsite());
    }

    private void setErrorDetails(String errorMessage) {
        mUserNameTextView.setText(errorMessage);
        mEmailTextView.setText("");
        mWebsiteTextView.setText("");
    }

    @Override
    public void onDestroyView() {
        mProfileViewModel.getUser().removeObservers(getViewLifecycleOwner());
        super.onDestroyView();
    }
}
