package com.example.myapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.config.Constant;
import com.example.myapplication.model.CancelRoomReq;
import com.example.myapplication.model.ReserveInfo;
import com.example.myapplication.model.Room;
import com.example.myapplication.model.RoomRes;
import com.example.myapplication.util.StringUtil;
import com.example.myapplication.util.TokenUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ShowActivity extends AppCompatActivity {

    Button buttonDateShow;
    TextView textViewDateShow;

    private int year;
    private int month;
    private int day;

    static final int DATE_DIALOG_ID = 555;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        addListenerOnButton();
    }

    public void addListenerOnButton() {

        buttonDateShow = (Button) findViewById(R.id.buttonDateShow);

        // default date meeting
        Calendar calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);
        textViewDateShow = (TextView) findViewById(R.id.dateShow);
        textViewDateShow.setText(new StringBuilder().append(StringUtil.pad(day))
                .append("/").append(StringUtil.pad(month+1)).append("/").append(year)
                .append(" "));

        buttonDateShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });

    }
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, datePickerShowListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerShowListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            textViewDateShow = (TextView) findViewById(R.id.dateShow);
            textViewDateShow.setText(new StringBuilder().append(StringUtil.pad(day))
                    .append("/").append(StringUtil.pad(month+1)).append("/").append(year)
                    .append(" "));

        }
    };

    public void doSearchShow(View view) {
        String date = textViewDateShow.getText().toString();

        Map<String, String> reqx = new HashMap<String, String>();
        reqx.put("Token",TokenUtil.getToken(getApplicationContext()));
        reqx.put("From",StringUtil.formatDatTime(date, "00:00"));
        reqx.put("To", StringUtil.formatDatTime(date, "23:59"));

        String json = StringUtil.makeJson(reqx);

        String availableRoom = callSlotAvailable(json);
        if(availableRoom==null){
            Toast.makeText(getApplicationContext(), "Cannot connect to server, Please try again.", Toast.LENGTH_LONG).show();
            return;
        }
        System.out.println("availableRoom = "+availableRoom);

        Gson gson = new Gson();
        RoomRes res = gson.fromJson(availableRoom, RoomRes.class);
        if(res != null){
            TokenUtil.saveToken(res.getToken(), getApplicationContext());
        } else {
            Toast.makeText(getApplicationContext(), "Connection Error", Toast.LENGTH_SHORT).show();
            return;
        }

//        System.out.println("slots = "+res.getSlots().length);

        if(res.getSlots()!=null && res.getSlots().length == 0){
            Toast.makeText(getApplicationContext(), "Meeting room not found", Toast.LENGTH_SHORT).show();
            return;
        }

        ArrayList<Room> roomList = null;
        if(res.getSlots()!= null && res.getSlots().length > 0){
            Room room = null;
            roomList = new ArrayList<Room>();
            for(Room item :res.getSlots()){
                room = new Room();
                room.setRoom(item.getRoom());
                room.setSizeMax(item.getSizeMax());
                room.setSizeMin(item.getSizeMin());
                room.setHasProjector(item.isHasProjector());
                room.setHasVC(item.isHasVC());
                room.setHasWB(item.isHasWB());

                room.setPlace(item.getPlace());
                room.setFloor(item.getFloor());
                room.setNumber(item.getNumber());
                room.setFrom(item.getFrom());
                room.setTo(item.getTo());
                room.setUser(item.getUser());
                room.setUserEN(item.getUserEN());
                room.setUserTH(item.getUserTH());
                room.setPhone(item.getPhone());
                room.setEmail(item.getEmail());
                room.setForUser(item.getForUser());
                room.setForUserEN(item.getForUserEN());
                room.setForUserTH(item.getForUserTH());
                room.setForPhone(item.getForPhone());
                room.setForEmail(item.getForEmail());
                room.setNote(item.getNote());
                room.setWhen(item.getWhen());

                roomList.add(room);
            }
            Collections.sort(roomList, new Comparator<Room>() {
                @Override
                public int compare(Room o1, Room o2) {
                    return o1.getRoom().compareTo(o2.getRoom());
                }
            });
        }

        Intent intent = new Intent(this,ShowListActivity.class);
        intent.putExtra("roomList",roomList);
        startActivity(intent);

    }

    private String callSlotAvailable(String json) {
        try {
            DownloadTask task = new DownloadTask();
            task.setUrl(Constant.SERVICE_SLOT_VIEW);
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
