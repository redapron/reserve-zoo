package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.config.Constant;
import com.example.myapplication.model.LoginRes;
import com.example.myapplication.model.MakeReq;
import com.example.myapplication.model.ReserveInfo;
import com.example.myapplication.util.StringUtil;
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

        MakeReq makeReq = new MakeReq();
        makeReq.setRoom(reserveInfo.getRoom());
        makeReq.setNote(reserveInfo.getTopic());
        makeReq.setToken(TokenUtil.getToken(getApplicationContext()));
        makeReq.setUser(TokenUtil.getUser(getApplicationContext()));
        makeReq.setFrom(StringUtil.formatDatTime(reserveInfo.getDateMeeting(),reserveInfo.getTimeStart()));
        makeReq.setTo(StringUtil.formatDatTime(reserveInfo.getDateMeeting(),reserveInfo.getTimeEnd()));

        //makeReq.setFrom("20170122 130000"); // test
        //makeReq.setTo("20170122 140000"); // test

        Gson gson = new Gson();
        String json = gson.toJson(makeReq);
        System.out.println("json = "+json);

        String makeResponse = callSlotMake(json);
        if(makeResponse==null){
            Toast.makeText(getApplicationContext(), "Cannot connect to server, Please try again.", Toast.LENGTH_LONG).show();
            return;
        }
        System.out.println("makeResponse = "+makeResponse);

        Type founderType = new TypeToken<LoginRes>(){}.getType();
        LoginRes res = gson.fromJson(makeResponse, founderType);

        if(res != null && res.getToken() != null){
            TokenUtil.saveToken(res.getToken(), getApplicationContext());
            if(res.getState()==0){
                Toast.makeText(getApplicationContext(), "Room:"+makeReq.getRoom()+" --> Reserved Successfully.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "Failed --> "+res.getError(), Toast.LENGTH_LONG).show();
            }
            Intent intent = new Intent(this,CenterpointActivity.class);
            startActivity(intent);
        } else if(res != null && res.getError() != null) {
            Toast.makeText(getApplicationContext(), "Failed --> "+res.getError(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Failed --> Cannot connect to server.", Toast.LENGTH_LONG).show();
        }

    }

    private String callSlotMake(String json) {
        try {
            DownloadTask task = new DownloadTask();
            //task.setUrl("http://10.215.101.76:5000/slot/make");;
            task.setUrl(Constant.SERVICE_SLOT_MAKE);
            task.setJson(json);
            String result = task.execute().get();
            return result;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
