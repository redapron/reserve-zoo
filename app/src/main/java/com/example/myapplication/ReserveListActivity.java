package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.model.AppContext;
import com.example.myapplication.model.ReserveInfo;
import com.example.myapplication.model.Room;

import java.util.ArrayList;
import java.util.List;

public class ReserveListActivity extends AppCompatActivity {

    List<Room> roomList;
    ListView listView;
    ReserveInfo reserveInfo;

    TextView listReserveDate;
    TextView listReserveTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_list);

        roomList = (ArrayList<Room>) getIntent().getSerializableExtra("roomList");
        System.out.println("roomList.size() = "+roomList.size());
        reserveInfo = (ReserveInfo) getIntent().getSerializableExtra("reserveInfo");

        listReserveDate = (TextView) findViewById(R.id.listReserveDate);
        listReserveTime = (TextView) findViewById(R.id.listReserveTime);
        listView = (ListView) findViewById(R.id.listViewReserve);

        listReserveDate.setText(reserveInfo.getDateMeeting());
        listReserveTime.setText(reserveInfo.getTimeStart()+" - "+reserveInfo.getTimeEnd());
        ReserveAdapter adapter = new ReserveAdapter(this,roomList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Room selItem = roomList.get(position);
                Intent intent = new Intent(ReserveListActivity.this,ReserveListDetailActivity.class);
                reserveInfo.setRoom(selItem.getRoom());
                reserveInfo.setSizeMin(selItem.getSizeMin());
                reserveInfo.setSizeMax(selItem.getSizeMax());
                intent.putExtra("reserveInfo",reserveInfo);
                startActivity(intent);
            }
        });
    }
}
