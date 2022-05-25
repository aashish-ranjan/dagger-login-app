package com.aashish.daggerloginapp.models;

import com.google.gson.annotations.SerializedName;

public class Post {
    @SerializedName("userId") private int userId;
    @SerializedName("id") private int id;
    @SerializedName("title") private String title;
    @SerializedName("body") private String body;

    public Post(int userId, int id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public Post() {}

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
