package com.example.youthcareapplication.user;

public class user {
    private String name;
    private String email;
    private int sex;
    private int age;
    private float height;
    private float weight;

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

    private float avgheight;
    private float avgweight;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}
