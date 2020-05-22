package com.example.youthcareapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FindAverage {
    boolean celar = false;


    private JsonPlaceHolderApi jsonPlaceHolderApi2;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.112.35:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private void getMale(int inputSex, int inputAge){

        final int sex = inputSex;
        final int age = inputAge;
        jsonPlaceHolderApi2 = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<PostInfo>> call = jsonPlaceHolderApi2.getPostInfo();

        call.enqueue(new Callback<List<PostInfo>>() {
            @Override
            public void onResponse(Call<List<PostInfo>> call, Response<List<PostInfo>> response) {
                if (!response.isSuccessful()) {
                    return;
                }

                List<PostInfo> postInfos = response.body();
                forout :
                for (PostInfo postInfo : postInfos) {

                    if (age == postInfo.getAge()) {
                        LoginActivity.userInformaiton.setAvgheight(postInfo.getHeight());
                        LoginActivity.userInformaiton.setAvgweight(postInfo.getWeight());
                        celar = true;
                        break forout;
                    }
                }


            }

            @Override
            public void onFailure(Call<List<PostInfo>> call, Throwable t) {

            }
        });
    }
    private void getFamale(int inputSex, int inputAge){
        final int sex = inputSex;
        final int age = inputAge;
        jsonPlaceHolderApi2 = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<PostInfo>> call = jsonPlaceHolderApi2.getPostInfo2();
        call.enqueue(new Callback<List<PostInfo>>() {
            @Override
            public void onResponse(Call<List<PostInfo>> call, Response<List<PostInfo>> response) {
                if (!response.isSuccessful()) {
                    return;
                }

                List<PostInfo> postInfos = response.body();

                forout :
                for (PostInfo postInfo : postInfos) {
                    if (age == postInfo.getAge()) {
                        LoginActivity.userInformaiton.setAvgheight(postInfo.getHeight());
                        LoginActivity.userInformaiton.setAvgweight(postInfo.getWeight());
                      break forout;
                    }
                }


            }

            @Override
            public void onFailure(Call<List<PostInfo>> call, Throwable t) {
            }
        });
    }

    public  void getInfo(int inputSex, int inputAge) {
        if (LoginActivity.userInformaiton.getSex() == 0) {
            getFamale(inputSex, inputAge);
        } else if (LoginActivity.userInformaiton.getSex() == 1) {
            getMale(inputSex, inputAge);
        }

    }


}

