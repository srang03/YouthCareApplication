package com.example.youthcareapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MyInformationFragment extends Fragment {


    String email = LoginActivity.userInformaiton.getEmail();

    String averageheight, averageweight;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myinformation, null);

        if(LoginActivity.userInformaiton.getWeight() == 0.0f){
            Toast.makeText(getActivity(), "정보입력을 해주세요", Toast.LENGTH_SHORT).show();
        }
        else {
            TextView my_height = view.findViewById(R.id.my_height);
            TextView average_height = view.findViewById(R.id.average_height);

            TextView my_weight = view.findViewById(R.id.my_weight);
            TextView average_weight = view.findViewById(R.id.average_weight);

            String myheight = Float.toString(LoginActivity.userInformaiton.getHeight());
            String myWeight = Float.toString(LoginActivity.userInformaiton.getWeight());

            averageheight = Float.toString(LoginActivity.userInformaiton.getAvgheight());
            averageweight = Float.toString(LoginActivity.userInformaiton.getAvgweight());


            TextView text_height_result = view.findViewById(R.id.text_height_result);
            TextView text_weight_result = view.findViewById(R.id.text_weight_result);

            ProgressBar height_progress = view.findViewById(R.id.height_progress);
            ProgressBar weight_progress = view.findViewById(R.id.weight_progress);



            int age = LoginActivity.userInformaiton.getAge();



            average_weight.setText(Float.toString(LoginActivity.userInformaiton.getAvgweight()));
            average_height.setText(Float.toString(LoginActivity.userInformaiton.getAvgheight()));

            my_height.setText(myheight);
            my_weight.setText(myWeight);


            float userheight = LoginActivity.userInformaiton.getHeight();
            float userWeight = LoginActivity.userInformaiton.getWeight();




            if (LoginActivity.userInformaiton.getHeight() > LoginActivity.userInformaiton.getAvgheight()) {
                text_height_result.setText(age + "세 기준 평균 키 보다 큽니다!");
                text_height_result.setTextColor(getResources().getColor(R.color.colorBlue));

                if ((50 + (int) (userheight - LoginActivity.userInformaiton.getAvgheight()) * 3) > 100) {
                    height_progress.setProgress(100);
                } else {
                    height_progress.setProgress(50 + (int) (userheight - LoginActivity.userInformaiton.getAvgheight()) * 3);
                }


            } else if (userheight == LoginActivity.userInformaiton.getAvgheight()) {
                text_height_result.setText(age + "세 기준 평균 키 입니다!");
                height_progress.setProgress(50);
            } else {
                text_height_result.setText(age + "세 기준 평균 키 보다 작습니다.");
                text_height_result.setTextColor(getResources().getColor(R.color.colorAccent));
                if (50 - (int) (LoginActivity.userInformaiton.getAvgheight() - userheight) * 3 < 0) {
                    height_progress.setProgress(0);
                } else {
                    height_progress.setProgress(50 - (int) (LoginActivity.userInformaiton.getAvgheight() - userheight) * 3);
                }


            }

            if (LoginActivity.userInformaiton.getWeight() > LoginActivity.userInformaiton.getAvgweight()) {
                text_weight_result.setText(age + "세 기준 평균 몸무게 보다 많이 나갑니다.");
                text_weight_result.setTextColor(getResources().getColor(R.color.colorAccent));

                if ((50 + (int) (userWeight - LoginActivity.userInformaiton.getAvgweight()) * 3) > 100) {
                    weight_progress.setProgress(100);
                } else {
                    weight_progress.setProgress(50 + (int) (userWeight - LoginActivity.userInformaiton.getAvgweight()) * 3);
                }

            } else if (userWeight == LoginActivity.userInformaiton.getAvgweight()) {
                text_weight_result.setText(age + "세 기준 평균 몸무게 입니다!");
                weight_progress.setProgress(50);
            } else {
                text_weight_result.setText(age + "세 기준 평균 몸무게 보다 작습니다.");
                text_weight_result.setTextColor(getResources().getColor(R.color.colorBlue));
                if (50 - (int) (LoginActivity.userInformaiton.getAvgweight() - userWeight) * 3 < 0) {
                    weight_progress.setProgress(0);
                } else {
                    weight_progress.setProgress(50 - (int) (LoginActivity.userInformaiton.getAvgweight() - userWeight) * 3);
                }
            }
//        new CountDownTimer(2000, 1000) {
//                    @Override
//                    public void onTick(long l) {
//
//                    }
//
//                    @Override
//                    public void onFinish() {
//                        Toast.makeText(getActivity(), "Complete to get Data", Toast.LENGTH_SHORT).show();
//                    }
//                }.start();
            if(MykeyFragment.prevAge == LoginActivity.userInformaiton.getAge() &&  MykeyFragment.prevHeight == LoginActivity.userInformaiton.getHeight() && MykeyFragment.prevWeight == LoginActivity.userInformaiton.getWeight()){

            }
            else {

            }

        }

        return view;

        //  return inflater.inflate(R.layout.fragment_myinformation, container, false);
    }

//


}

//
//
