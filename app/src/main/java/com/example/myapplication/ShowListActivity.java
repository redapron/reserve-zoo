package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.myapplication.model.Room;

import java.util.ArrayList;
import java.util.List;

public class ShowListActivity extends AppCompatActivity {

    List<Room> roomList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list);

        roomList = (ArrayList<Room>) getIntent().getSerializableExtra("roomList");

        listView = (ListView) findViewById(R.id.listViewShow);
        ShowAdapter adapter = new ShowAdapter(this,roomList);
        listView.setAdapter(adapter);
    }
}
