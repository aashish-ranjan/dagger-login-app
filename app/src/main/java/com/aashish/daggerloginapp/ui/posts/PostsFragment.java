package com.aashish.daggerloginapp.ui.posts;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
                            Log.d(TAG, "onChanged: Posts fetched successfully");
                            initRecyclerView(postListResponse.data);
                            break;
                        }
                        case LOADING: {
                            Log.d(TAG, "onChanged: fetching posts..");
                            break;
                        }
                        case ERROR: {
                            Log.e(TAG, "onChanged: Error fetching posts");
                            break;
                        }
                    }
                }
            }
        });
    }

    private void initRecyclerView(List<Post> postList) {
        adapter.setPosts(postList);
        mPostRecyclerView.setAdapter(adapter);
        mPostRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
