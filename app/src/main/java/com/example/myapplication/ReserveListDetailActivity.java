package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.model.ReserveInfo;

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
}
