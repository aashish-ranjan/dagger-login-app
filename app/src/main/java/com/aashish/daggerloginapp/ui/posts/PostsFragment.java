package com.aashish.daggerloginapp.ui.posts;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aashish.daggerloginapp.R;
import com.aashish.daggerloginapp.models.Post;
import com.aashish.daggerloginapp.models.Response;
import com.aashish.daggerloginapp.viewmodels.PostsViewModel;
import com.aashish.daggerloginapp.viewmodels.ViewModelProviderFactory;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class PostsFragment extends DaggerFragment {
    private static final String TAG = "PostsFragment";
    private RecyclerView mPostRecyclerView;
    private ProgressBar mProgressBar;

    private PostsViewModel mPostsViewModel;

    @Inject
    PostsRecyclerViewAdapter adapter;

    @Inject
    ViewModelProviderFactory mViewModelProviderFactory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_posts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPostRecyclerView = view.findViewById(R.id.rvPosts);
        mProgressBar = view.findViewById(R.id.postsProgressBar);
        mPostsViewModel = new ViewModelProvider(this, mViewModelProviderFactory).get(PostsViewModel.class);
        subscribeObservers();
    }

    private void subscribeObservers() {
        mPostsViewModel.getPostList().observe(getViewLifecycleOwner(), new Observer<Response<List<Post>>>() {
            @Override
            public void onChanged(Response<List<Post>> postListResponse) {
                if (postListResponse != null) {
                    switch (postListResponse.status) {
                        case SUCCESS: {
                            showProgressBar(false);
                            Log.d(TAG, "onChanged: Posts fetched successfully");
                            initRecyclerView(postListResponse.data);
                            break;
                        }
                        case LOADING: {
                            showProgressBar(true);
                            Log.d(TAG, "onChanged: fetching posts..");
                            break;
                        }
                        case ERROR: {
                            showProgressBar(false);
                            Log.e(TAG, "onChanged: Error fetching posts");
                            break;
                        }
                        default: {
                            showProgressBar(false);
                        }
                    }
                }
            }
        });
    }

    private void showProgressBar(Boolean showProgress) {
        if (showProgress) {
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            mProgressBar.setVisibility(View.GONE);
        }
    }


    private void initRecyclerView(List<Post> postList) {
        adapter.setPosts(postList);
        mPostRecyclerView.setAdapter(adapter);
        mPostRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
