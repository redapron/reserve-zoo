package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.model.Room;

public class CancleDetailActivity extends AppCompatActivity {

    Room roomInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancle_detail);

        roomInfo = (Room) getIntent().getSerializableExtra("roomInfo");
    }

    public void doCancleConfirm(View view){

    }
}
