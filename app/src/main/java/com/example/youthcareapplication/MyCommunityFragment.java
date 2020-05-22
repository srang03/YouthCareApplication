package com.example.youthcareapplication;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youthcareapplication.community.CommunityAdapter;
import com.example.youthcareapplication.community.CommunityItem;
import com.example.youthcareapplication.community.WriteActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyCommunityFragment extends Fragment {
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    public static final String EXTRA_FIRST = "first";
    public static ArrayList<CommunityItem> mCommunityList;

    private RecyclerView mRecyclerView;
    private CommunityAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    Button writeButton;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.112.35:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<PostCommunity>> call = jsonPlaceHolderApi.getPostNotice();

        call.enqueue(new Callback<List<PostCommunity>>() {
            @Override
            public void onResponse(Call<List<PostCommunity>> call, Response<List<PostCommunity>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getActivity(),response.code(), Toast.LENGTH_LONG).show();
                    return;
                }

                List<PostCommunity> posts = response.body();
                mCommunityList = new ArrayList<>();
                for(PostCommunity post : posts) {

                    mCommunityList.add(new CommunityItem(post.getTitle() , post.getDate(), post.getName() , post.getText()));
                }

            }

            @Override
            public void onFailure(Call<List<PostCommunity>> call, Throwable t) {
                Toast.makeText(getActivity(), "connection Fail", Toast.LENGTH_LONG).show();
            }
        });




        View view = inflater.inflate(R.layout.fragment_mycommunity, null);
        writeButton = view.findViewById(R.id.writeButton);





        mRecyclerView = view.findViewById(R.id.recycleView);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
//        Log.d("값이 오는지",mCommunityList.get(0).getmText1());
        mAdapter = new CommunityAdapter(mCommunityList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new CommunityAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //open new intentgetItemCount
                Intent intent = new Intent(getActivity(), ActivityContent.class);
                intent.putExtra(EXTRA_FIRST, mCommunityList.get(position));
                startActivity(intent);
            }
        });


        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getActivity(), WriteActivity.class);
                startActivity(intent2);
            }
        });



      return view;
    }


}
