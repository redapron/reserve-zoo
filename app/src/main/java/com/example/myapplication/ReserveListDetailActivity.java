package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.model.LoginRes;
import com.example.myapplication.model.ReserveInfo;
import com.example.myapplication.util.TokenUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class ReserveListDetailActivity extends AppCompatActivity {

    TextView reserveDetailDate;
    TextView reserveDetailTime;
    TextView reserveDetailRoom;
    TextView reserveDetailSize;
    TextView reserveDetailTopic;
    TextView reserveDetailUserFor;

    ReserveInfo reserveInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_list_detail);

        reserveInfo = (ReserveInfo) getIntent().getSerializableExtra("reserveInfo");

        reserveDetailDate = (TextView)findViewById(R.id.reserveDetailDate);
        reserveDetailTime = (TextView)findViewById(R.id.reserveDetailTime);
        reserveDetailRoom = (TextView)findViewById(R.id.reserveDetailRoom);
        reserveDetailSize = (TextView)findViewById(R.id.reserveDetailSize);
        reserveDetailTopic = (TextView)findViewById(R.id.reserveDetailTopic);
        reserveDetailUserFor = (TextView)findViewById(R.id.reserveDetailUserFor);

        reserveDetailDate.setText(reserveInfo.getDateMeeting());
        reserveDetailTime.setText(reserveInfo.getTimeStart()+" - "+reserveInfo.getTimeEnd());
        reserveDetailRoom.setText(reserveInfo.getRoom());
        reserveDetailSize.setText(String.valueOf(reserveInfo.getSizeMin())+"-"+String.valueOf(reserveInfo.getSizeMax()));
        reserveDetailTopic.setText(reserveInfo.getTopic());
        reserveDetailUserFor.setText(reserveInfo.getUserIdMeeting());
    }

    public void doMakeConfirm(View view){

    }
}
