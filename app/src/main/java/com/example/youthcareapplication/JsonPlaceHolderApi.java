package com.example.youthcareapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("users")
    Call<List<Post>> getPosts();


    @GET("info")
    Call<List<PostInfo>> getPostInfo();


    @GET("info2")
    Call<List<PostInfo>> getPostInfo2();

    @GET("notice")
    Call<List<PostCommunity>> getPostNotice();
}
