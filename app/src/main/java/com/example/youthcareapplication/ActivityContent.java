package com.example.youthcareapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.youthcareapplication.community.CommunityItem;

public class ActivityContent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        Intent intent = getIntent();
        CommunityItem communityItem =intent.getParcelableExtra(MyCommunityFragment.EXTRA_FIRST);

        String title = communityItem.getmText1();
        String name  = communityItem.getmText2();
        String date = communityItem.getmText3();
        String content = communityItem.getmText4();

        TextView textView1 = findViewById(R.id.content_title_TextView);
        textView1.setText(title);

        TextView textView2 = findViewById(R.id.content_name_TextView);
        textView2.setText(name);

        TextView textView3 = findViewById(R.id.content_date_TextView);
        textView3.setText(date);

        TextView textView4 = findViewById(R.id.content_content_TextView);
        textView4.setText(content);

    }
}
