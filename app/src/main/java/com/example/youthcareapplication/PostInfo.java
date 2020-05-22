package com.example.youthcareapplication;

import com.google.gson.annotations.SerializedName;

public class PostInfo {

    @SerializedName("age")
    private int age;
    private float weight;
    private float height;



    public int getAge() {
        return age;
    }

    public float getWeight() {
        return weight;
    }

    public float getHeight() {
        return height;
    }
}