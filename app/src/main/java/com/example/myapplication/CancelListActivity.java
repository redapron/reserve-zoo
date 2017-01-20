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

public class CancelListActivity extends AppCompatActivity {

    List<Room> roomList;
    ListView listView;
    Room roomInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_list);

        roomList = (ArrayList<Room>) getIntent().getSerializableExtra("roomList");

        listView = (ListView) findViewById(R.id.listViewCancel);
        CancelAdapter adapter = new CancelAdapter(this,roomList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Room selItem = roomList.get(position);
                Intent intent = new Intent(CancelListActivity.this,CancleDetailActivity.class);
                roomInfo = roomList.get(position);
                intent.putExtra("roomInfo",roomInfo);
                startActivity(intent);
            }
        });
    }
}
