package com.example.youthcareapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.youthcareapplication.user.user;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    Button button_login;
    EditText editText_id;
    EditText editText_pw ;

//    public  static FindAverage findAverage = new FindAverage();
    public static user userInformaiton = new user();




    private JsonPlaceHolderApi jsonPlaceHolderApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button_login = findViewById(R.id.button_login);
        editText_id = (EditText) findViewById(R.id.editText_id);
        editText_pw = (EditText) findViewById(R.id.editText_pw);

        TextView textView_singup = (TextView) findViewById(R.id.textView_singup);
        textView_singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupAcitivity.class);
                Log.d("Test", "실행중");
                LoginActivity.this.startActivity(intent);
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.112.35:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);


        button_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(editText_id.getText().toString().length() == 0){
                    Toast.makeText(LoginActivity.this, "아이디 비밀번호를 입력해주세요", Toast.LENGTH_LONG).show();
                }
                else {
                    getPost(editText_id.getText().toString(), editText_pw.getText().toString());

                }
            }
        });


    }
    private void getPost(String inputid, String inputpw){

        final String id = inputid;
        final String pw = inputpw;
        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                boolean result = false;
                if(!response.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, response.code(), Toast.LENGTH_LONG).show();
                    return;
                }
                List<Post> posts = response.body();

                for(Post post : posts){

                    if((post.getId().equals(id)) && (post.getPassword().equals(pw))){
                        userInformaiton.setName(post.getName());
                        userInformaiton.setEmail(post.getId());
                        userInformaiton.setAge(post.getAge());
                        userInformaiton.setSex(post.getSex());
                        userInformaiton.setHeight(post.getHeight());
                        userInformaiton.setWeight(post.getWeight());
                        userInformaiton.setAvgheight(post.getAvgheight());
                        userInformaiton.setAvgweight(post.getAvgweight());

                        if(7 <userInformaiton.getAge() && 20 > userInformaiton.getAge()) {

                        }
                        result = true;
                        break;
                    }
                }
                if (result == true){
                    Toast.makeText(LoginActivity.this,  userInformaiton.getName() + "님 반갑습니다.", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);

                }
                else {
                    Toast.makeText(LoginActivity.this, "Do you want to sign up?", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "connection Fail", Toast.LENGTH_LONG).show();
            }
        });
    }

}
