package com.example.myapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.myapplication.config.Constant;
import com.example.myapplication.model.CancelRoomReq;
import com.example.myapplication.model.LoginRes;
import com.example.myapplication.model.ReserveInfo;
import com.example.myapplication.model.Room;
import com.example.myapplication.model.RoomReq;
import com.example.myapplication.model.RoomRes;
import com.example.myapplication.model.SearchCancelResult;
import com.example.myapplication.util.StringUtil;
import com.example.myapplication.util.TokenUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.internal.Util;

public class CancelActivity extends AppCompatActivity {

    Button buttonDateCancel;
    TextView textViewDateCancel;

    private int year;
    private int month;
    private int day;

    static final int DATE_DIALOG_ID = 411;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel);

        addListenerOnButton();
    }

    public void addListenerOnButton() {

        buttonDateCancel = (Button) findViewById(R.id.buttonDateCancel);

        // default date meeting
        Calendar calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);
        textViewDateCancel = (TextView) findViewById(R.id.dateCancel);
        textViewDateCancel.setText(new StringBuilder().append(StringUtil.pad(day))
                .append("/").append(StringUtil.pad(month+1)).append("/").append(year)
                .append(" "));

        buttonDateCancel.setOnClickListener(new View.OnClickListener() {
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
                return new DatePickerDialog(this, datePickerCancelListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerCancelListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            textViewDateCancel = (TextView) findViewById(R.id.dateCancel);
            textViewDateCancel.setText(new StringBuilder().append(StringUtil.pad(day))
                    .append("/").append(StringUtil.pad(month+1)).append("/").append(year)
                    .append(" "));

        }
    };

    public void doSearchCancel(View view) {
        String date = textViewDateCancel.getText().toString().trim();

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date selectDate = formatter.parse(date);
            Date currentDate = formatter.parse(formatter.format(new Date()));

            long diff = selectDate.getTime() - currentDate.getTime();
            long duration = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
            if (duration >= 90) {
                Toast.makeText(getApplicationContext(), "โปรดเลือก วันที่ประชุม ล่วงหน้าไม่เกิน 90 วัน", Toast.LENGTH_LONG).show();
                return;
            } else if (duration < 0) {
                Toast.makeText(getApplicationContext(), "โปรดเลือก วันที่ประชุม มากกว่าหรือเท่ากับ วันที่ปัจจุบัน", Toast.LENGTH_LONG).show();
                return;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        CancelRoomReq req = new CancelRoomReq();
        req.setToken(TokenUtil.getToken(getApplicationContext()));
        req.setUser(TokenUtil.getUser(getApplicationContext()));
        req.setFrom(StringUtil.formatDatTime(date, "00:00"));
        req.setTo(StringUtil.formatDatTime(date, "23:59"));

        Gson gson = new Gson();
        String json = gson.toJson(req);

        Log.i("bui", "json: " + json);

        String availableRoom = callSlotAvailable(json);
        if(availableRoom==null){
            Toast.makeText(getApplicationContext(), "Cannot connect to server, Please try again.", Toast.LENGTH_LONG).show();
            return;
        }
        RoomRes res = gson.fromJson(availableRoom, RoomRes.class);
        TokenUtil.saveToken(res.getToken(), getApplicationContext());

        Log.i("bui", "res: " + res.getError() + res.getToken() + res.getState() + "end");

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

        Intent intent = new Intent(CancelActivity.this,CancelListActivity.class);
        intent.putExtra("roomList",roomList);
        startActivity(intent);

    }

    public String makeJson(Map<String,String> m) {
        Iterator<Map.Entry<String, String>> it = m.entrySet().iterator();
        String rtn = "{";
        while (it.hasNext()) {
            Map.Entry<String, String> pair =  it.next();
            rtn += String.format("'%s': '%s', ", pair.getKey(), pair.getValue());
        }
        rtn += "}";
        Log.i("bui", rtn);
        return rtn;
    }

    private String callSlotAvailable(String json) {
        try {
            DownloadTask task = new DownloadTask();
            //task.setUrl("http://10.215.101.76:5000/slot/view");
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
