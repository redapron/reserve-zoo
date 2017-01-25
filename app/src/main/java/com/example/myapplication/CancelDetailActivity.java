package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.config.Constant;
import com.example.myapplication.model.CancelRoomRes;
import com.example.myapplication.model.ConfirmCancelRoomReq;
import com.example.myapplication.model.LoginRes;
import com.example.myapplication.model.Room;
import com.example.myapplication.util.StringUtil;
import com.example.myapplication.util.TokenUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class  CancelDetailActivity extends AppCompatActivity {

    Room roomInfo;
    TextView cancelDetailDate;
    TextView cancelDetailTime;
    TextView cancelDetailRoom;
    TextView cancelDetailNote;
    TextView cancelDetailForName;
    TextView cancelDetailForPhone;
    TextView cancelDetailUserName;
    TextView cancelDetailUserPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_detail);

        roomInfo = (Room) getIntent().getSerializableExtra("roomInfo");

        cancelDetailDate = (TextView) findViewById(R.id.cancelDetailDate);
        cancelDetailTime = (TextView) findViewById(R.id.cancelDetailTime);
        cancelDetailRoom = (TextView) findViewById(R.id.cancelDetailRoom);
        cancelDetailNote = (TextView) findViewById(R.id.cancelDetailNote);
        cancelDetailForName = (TextView) findViewById(R.id.cancelDetailForName);
        cancelDetailForPhone = (TextView) findViewById(R.id.cancelDetailForPhone);
        cancelDetailUserName = (TextView) findViewById(R.id.cancelDetailUserName);
        cancelDetailUserPhone = (TextView) findViewById(R.id.cancelDetailUserPhone);

        cancelDetailDate.setText(StringUtil.getClientDateFormat(roomInfo.getFrom()));
        cancelDetailTime.setText(StringUtil.getClientTimeFormat(roomInfo.getFrom())+"-"+StringUtil.getClientTimeFormat(roomInfo.getTo()));
        cancelDetailRoom.setText(roomInfo.getRoom());
        cancelDetailNote.setText(roomInfo.getNote());
        cancelDetailForName.setText(roomInfo.getForUserTH());
        cancelDetailForPhone.setText(roomInfo.getForPhone());
        cancelDetailUserName.setText(roomInfo.getUserTH());
        cancelDetailUserPhone.setText(roomInfo.getPhone());
    }

    public void doCancelConfirm(View view) {

        ConfirmCancelRoomReq req = new ConfirmCancelRoomReq();
        req.setToken(TokenUtil.getToken(getApplicationContext()));
        req.setUser(TokenUtil.getUser(getApplicationContext()));
        req.setFrom(roomInfo.getFrom());
        req.setTo(roomInfo.getTo());
        req.setRoom(roomInfo.getRoom());

        Gson gson = new Gson();
        String json = gson.toJson(req);

        Log.i("bui", "doSearchCancel json: " + json);

        String cancelResult = callDelete(json);
        Log.i("bui", "cancel result: " + cancelResult);

        Type founderType = new TypeToken<LoginRes>(){}.getType();
        LoginRes res = gson.fromJson(cancelResult, founderType);

        //TokenUtil.saveToken(res.getToken(), getApplicationContext());

        Log.i("bui", "doSearchCancel res: " + res.getError() + res.getToken() + res.getState() + "end");

        if(res != null && res.getToken() != null){
            TokenUtil.saveToken(res.getToken(), getApplicationContext());
            if(res.getState()==0){
                Toast.makeText(getApplicationContext(), "Room:"+roomInfo.getRoom()+" --> Cancelled Successfully.", Toast.LENGTH_LONG).show();
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

    private String callDelete(String json) {
        try {
            DeleteTask task = new DeleteTask();
            //task.setUrl("http://10.215.101.76:5000/slot/free");
            task.setUrl(Constant.SERVICE_SLOT_CANCEL);
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
