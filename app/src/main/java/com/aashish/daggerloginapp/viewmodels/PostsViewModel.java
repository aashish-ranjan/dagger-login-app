package com.aashish.daggerloginapp.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.aashish.daggerloginapp.SessionManager;
import com.aashish.daggerloginapp.models.Post;
import com.aashish.daggerloginapp.models.Response;
import com.aashish.daggerloginapp.network.main.MainApi;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PostsViewModel extends ViewModel {
    private static final String TAG = "PostsViewModel";

    private MediatorLiveData<Response<List<Post>>> _postListResponse;

    private MainApi mMainApi;
    private SessionManager mSessionManager;

    @Inject
    public PostsViewModel(MainApi mainApi, SessionManager sessionManager) {
        this.mMainApi = mainApi;
        this.mSessionManager = sessionManager;
        Log.d(TAG, "PostsViewModel is ready..");
        initUserPosts();
    }

    public void initUserPosts() {
        if (_postListResponse == null) {
             _postListResponse = new MediatorLiveData<>();
            _postListResponse.setValue(Response.loading(null));
            int userId = mSessionManager.getAuthenticatedUser().getValue().data.getId();
            LiveData<Response<List<Post>>> source = fetchUserPostsByUserId(userId);

            _postListResponse.addSource(source, new Observer<Response<List<Post>>>() {
                @Override
                public void onChanged(Response<List<Post>> postListResponse) {
                    _postListResponse.setValue(postListResponse);
                    _postListResponse.removeSource(source);
                }
            });
        }
    }

    private LiveData<Response<List<Post>>> fetchUserPostsByUserId(int userId) {
        return LiveDataReactiveStreams.fromPublisher(
                mMainApi.getPostsByUserId(userId)
                        .onErrorReturn(new Function<Throwable, List<Post>>() {
                            @Override
                            public List<Post> apply(Throwable throwable) throws Throwable {
                                Log.e(TAG, "apply: " + throwable.getMessage());
                                Post post = new Post();
                                post.setUserId(-1);
                                List<Post> postList = new ArrayList<>();
                                postList.add(post);
                                return postList;
                            }
                        })
                        .map(new Function<List<Post>, Response<List<Post>>>() {
                            @Override
                            public Response<List<Post>> apply(List<Post> posts) throws Throwable {
                                if (posts.size() > 0 && posts.get(0).getId() != -1) {
                                    return Response.success(posts);
                                } else {
                                    return Response.error(null, "Error fetching posts");
                                }
                            }
                        })
                        .subscribeOn(Schedulers.io())
        );
    }

    public LiveData<Response<List<Post>>> getPostList() {
        return _postListResponse;
    }
}
