package com.aashish.daggerloginapp.models;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id") private int id;
    @SerializedName("username") private String userName;
    @SerializedName("email") private String email;
    @SerializedName("website") private String website;

    public User(int id, String userName, String email, String website) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.website = website;
    }

    public User() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
