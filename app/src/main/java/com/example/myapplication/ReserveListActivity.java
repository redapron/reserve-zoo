package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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


        //ArrayList<Room> roomList = (ArrayList<Room>) getIntent().getExtras().getSerializable("xx");
//        System.out.println(roomList.size());
//        ListView listView = (ListView) findViewById(R.id.listViewReserve);
//        ArrayList<Room> list = new ArrayList<>();
//        Room a = new Room();
//        a.setRoom("aaa");
//        a.setSizeMax(5);
//        list.add(a);
//        Room b = new Room();
//        b.setRoom("bbb");
//        b.setSizeMax(7);
//        list.add(b);
//        ReserveAdapter adapter = new ReserveAdapter(this,list);
//        listView.setAdapter(adapter);
    }
}
