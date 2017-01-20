package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.myapplication.model.Room;

import java.util.ArrayList;

public class ReserveListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_list);

        ListView listView = (ListView) findViewById(R.id.listViewReserve);
        ArrayList<Room> list = new ArrayList<>();
        Room a = new Room();
        a.setRoom("aaa");
        a.setSizez("10-20");
        list.add(a);
        Room b = new Room();
        b.setRoom("bbb");
        b.setSizez("3-5");
        list.add(b);
        ReserveAdapter adapter = new ReserveAdapter(this,list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ReserveListActivity.this,ReserveListDetailActivity.class);
                startActivity(intent);
            }
        });
    }
}
