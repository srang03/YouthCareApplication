package com.example.youthcareapplication;

import com.google.gson.annotations.SerializedName;

public class PostCommunity {
    private String title;
    private String name;
    private String date;

    @SerializedName("content")
    private String text;

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getText() {
        return text;
    }
}
