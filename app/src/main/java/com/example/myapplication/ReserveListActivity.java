package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.myapplication.model.AppContext;
import com.example.myapplication.model.Room;

import java.util.ArrayList;
import java.util.List;

public class ReserveListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_list);


        List<Room> roomList = AppContext.getInstance().getRoomList();
        System.out.println("roomList.size() = "+roomList.size());

    }
}
