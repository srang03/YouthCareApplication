package com.example.youthcareapplication;

import com.google.gson.annotations.SerializedName;

public class Post {

    @SerializedName("email")
    private String id;
    private String password;
    private String name;
    private int sex;
    private int age;
    private float height;
    private float weight;
    private float avgheight;
    private float avgweight;

    public float getAvgheight() {
        return avgheight;
    }

    public void setAvgheight(float avgheight) {
        this.avgheight = avgheight;
    }

    public float getAvgweight() {
        return avgweight;
    }

    public void setAvgweight(float avgweight) {
        this.avgweight = avgweight;
    }

    public String getName() {
        return name;
    }

    public Post(String id, String password, String name, int sex) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.sex = sex;
    }

    public int getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public float getHeight() {
        return height;
    }

    public float getWeight() {
        return weight;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

}
