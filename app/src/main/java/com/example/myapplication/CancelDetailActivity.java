package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.myapplication.model.CancelRoomRes;
import com.example.myapplication.model.ConfirmCancelRoomReq;
import com.example.myapplication.model.Room;
import com.example.myapplication.util.StringUtil;
import com.example.myapplication.util.TokenUtil;
import com.google.gson.Gson;

public class CancelDetailActivity extends AppCompatActivity {

    Room roomInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_detail);

        roomInfo = (Room) getIntent().getSerializableExtra("roomInfo");
    }

    public void doSearchCancel(View view) {

        String date = "";

        ConfirmCancelRoomReq req = new ConfirmCancelRoomReq();
        req.setToken(TokenUtil.getToken(getApplicationContext()));
        req.setUser(TokenUtil.getUser(getApplicationContext()));
        req.setFrom(StringUtil.formatDatTime(date, "00:00"));
        req.setTo(StringUtil.formatDatTime(date, "23:59"));

        Gson gson = new Gson();
        String json = gson.toJson(req);

        Log.i("bui", "doSearchCancel json: " + json);

        String availableRoom = callDelete(json);
        CancelRoomRes res = gson.fromJson(availableRoom, CancelRoomRes.class);
        TokenUtil.saveToken(res.getToken(), getApplicationContext());

        Log.i("bui", "doSearchCancel res: " + res.getError() + res.getToken() + res.getState() + "end");

    }

    private String callDelete(String json) {
        try {
            DeleteTask task = new DeleteTask();
            task.setUrl("http://10.215.101.76:5000/slot/view");
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
