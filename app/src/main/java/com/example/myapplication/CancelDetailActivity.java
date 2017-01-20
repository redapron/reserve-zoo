package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_detail);

        roomInfo = (Room) getIntent().getSerializableExtra("roomInfo");
    }

    public void doCancelConfirm(View view) {

        ConfirmCancelRoomReq req = new ConfirmCancelRoomReq();
        req.setToken(TokenUtil.getToken(getApplicationContext()));
        req.setUser(TokenUtil.getUser(getApplicationContext()));
        req.setFrom(roomInfo.getFrom());
        req.setTo(roomInfo.getTo());

        Gson gson = new Gson();
        String json = gson.toJson(req);

        Log.i("bui", "doSearchCancel json: " + json);

        String cancelResult = callDelete(json);
        Log.i("bui", "cancel result: " + cancelResult);

        Type founderType = new TypeToken<LoginRes>(){}.getType();
        LoginRes res = gson.fromJson(cancelResult, founderType);

        TokenUtil.saveToken(res.getToken(), getApplicationContext());

        Log.i("bui", "doSearchCancel res: " + res.getError() + res.getToken() + res.getState() + "end");

    }

    private String callDelete(String json) {
        try {
            DeleteTask task = new DeleteTask();
            task.setUrl("http://10.215.101.76:5000/slot/free");
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
