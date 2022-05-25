package com.aashish.daggerloginapp.ui.posts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aashish.daggerloginapp.R;
import com.aashish.daggerloginapp.models.Post;

import java.util.List;

public class PostsRecyclerViewAdapter extends RecyclerView.Adapter<PostsRecyclerViewAdapter.PostRecyclerViewHolder> {
    private List<Post> mPostList;

    public PostsRecyclerViewAdapter() {}

    public void setPosts(List<Post> mPostList) {
        this.mPostList = mPostList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PostRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new PostRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostRecyclerViewHolder holder, int position) {
        holder.mUserId.setText(String.valueOf(mPostList.get(position).getUserId()));
        holder.mTitle.setText(mPostList.get(position).getTitle());
        holder.mBody.setText(mPostList.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return mPostList.size();
    }

    static class PostRecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView mUserId, mTitle, mBody;

        public PostRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            mUserId = itemView.findViewById(R.id.tvUserId);
            mTitle = itemView.findViewById(R.id.tvTitle);
            mBody = itemView.findViewById(R.id.tvBody);
        }
    }
}
