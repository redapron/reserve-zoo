package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SearchResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        String dateMeetingStr = getIntent().getStringExtra("date");
//        String timeStartStr = getIntent().getStringExtra("timeStart");
//        String timeEndStr = getIntent().getStringExtra("timeEnd");

//        TextView textViewDateHeader = (TextView)findViewById(R.id.textViewDateHeader);
//        textViewDateHeader.setText(dateMeetingStr);
//
//        TextView textViewTimeHeader = (TextView)findViewById(R.id.textViewTimeHeader);
//        textViewTimeHeader.setText(timeStartStr +" - "+timeEndStr);

        setContentView(R.layout.activity_search_result);
    }
}
