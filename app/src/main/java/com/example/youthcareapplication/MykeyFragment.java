package com.example.youthcareapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.youthcareapplication.community.WriteActivity;

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
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MykeyFragment extends Fragment {
    static int prevAge = LoginActivity.userInformaiton.getAge();
    static float prevHeight = LoginActivity.userInformaiton.getHeight();
    static float prevWeight = LoginActivity.userInformaiton.getWeight();

    EditText age, height, weight;
    String email = LoginActivity.userInformaiton.getEmail();

    boolean celar = false;

    private JsonPlaceHolderApi jsonPlaceHolderApi2;
    public static FindAverage findAverage = new FindAverage();
    Retrofit retrofit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mykey, null);
        Button btn = view.findViewById(R.id.save);
        age = view.findViewById(R.id.updateAge);
        height = view.findViewById(R.id.updateHeight);
        weight = view.findViewById(R.id.updateWeight);
//버튼이 클릭되면 여기 리스너로 옴
        btn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("저장하시겠습니까?")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getContext(), "My key가 변경되었습니다.", Toast.LENGTH_SHORT).show();
                        LoginActivity.userInformaiton.setAge(Integer.parseInt(age.getText().toString()));


                        LoginActivity.userInformaiton.setHeight(Float.valueOf(height.getText().toString()));
                        LoginActivity.userInformaiton.setWeight(Float.valueOf(weight.getText().toString()));
                        //            LoginActivity.findAverage.getInfo(LoginActivity.userInformaiton.getSex(), Integer.parseInt(age.getText().toString()));
                        findAverage.getInfo(LoginActivity.userInformaiton.getSex(), LoginActivity.userInformaiton.getAge());


                        retrofit = new Retrofit.Builder()
                                .baseUrl("http://192.168.112.35:3000/")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        new JSONTask().execute("http://10.0.2.2:3000/update");//AsyncTask 시작시킴
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                    }
                }).setNegativeButton("Cancel", null);
                AlertDialog alert= builder.create();
                alert.show();

            }
        });
        return view;

    }

    private void getMale(int inputSex, int inputAge) {

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
                forout:
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

    private void getFamale(int inputSex, int inputAge) {
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

                forout:
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

    public void getInfo(int inputSex, int inputAge) {
        if (LoginActivity.userInformaiton.getSex() == 0) {
            getFamale(inputSex, inputAge);
        } else if (LoginActivity.userInformaiton.getSex() == 1) {
            getMale(inputSex, inputAge);
        }

    }

    public class JSONTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... urls) {
            try {
                //JSONObject를 만들고 key value 형식으로 값을 저장해준다.

                JSONObject jsonObject = new JSONObject();
                jsonObject.accumulate("email", email);


                jsonObject.accumulate("age", LoginActivity.userInformaiton.getAge());
                jsonObject.accumulate("height", LoginActivity.userInformaiton.getHeight());
                jsonObject.accumulate("weight", LoginActivity.userInformaiton.getWeight());
                jsonObject.accumulate("aheight", LoginActivity.userInformaiton.getAvgheight());
                jsonObject.accumulate("aweight", LoginActivity.userInformaiton.getAvgweight());


//                jsonObject.accumulate("aheight", LoginActivity.averageinfo.getHeight());
//                jsonObject.accumulate("aweight", LoginActivity.averageinfo.getWeight());



                HttpURLConnection con = null;
                BufferedReader reader = null;

                try{
                    //URL url = new URL("http://192.168.25.16:3000/users");
                    URL url = new URL(urls[0]);
                    //연결을 함
                    con = (HttpURLConnection) url.openConnection();

                    con.setRequestMethod("POST");//POST방식으로 보냄
                    con.setRequestProperty("Cache-Control", "no-cache");//캐시 설정
                    con.setRequestProperty("Content-Type", "application/json");//application JSON 형식으로 전송
                    con.setRequestProperty("Accept", "text/html");//서버에 response 데이터를 html로 받음
                    con.setDoOutput(true);//Outstream으로 post 데이터를 넘겨주겠다는 의미
                    con.setDoInput(true);//Inputstream으로 서버로부터 응답을 받겠다는 의미
                    con.connect();

                    //서버로 보내기위해서 스트림 만듬
                    OutputStream outStream = con.getOutputStream();
                    //버퍼를 생성하고 넣음
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outStream));
                    writer.write(jsonObject.toString());
                    writer.flush();
                    writer.close();//버퍼를 받아줌

                    //서버로 부터 데이터를 받음
                    InputStream stream = con.getInputStream();

                    reader = new BufferedReader(new InputStreamReader(stream));

                    StringBuffer buffer = new StringBuffer();

                    String line = "";
                    while((line = reader.readLine()) != null){
                        buffer.append(line);
                    }

                    return buffer.toString();//서버로 부터 받은 값을 리턴해줌 아마 OK!!가 들어올것임

                } catch (MalformedURLException e){
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if(con != null){
                        con.disconnect();
                    }
                    try {
                        if(reader != null){
                            reader.close();//버퍼를 닫아줌
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }


    }


}








