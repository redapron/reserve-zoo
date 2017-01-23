package com.example.myapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.myapplication.model.AppContext;
import com.example.myapplication.model.LoginRes;
import com.example.myapplication.model.ReserveInfo;
import com.example.myapplication.model.Room;
import com.example.myapplication.model.RoomReq;
import com.example.myapplication.model.RoomRes;
import com.example.myapplication.util.StringUtil;
import com.example.myapplication.util.TokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ReserveActivity extends AppCompatActivity {

    Button buttonTimeStart;
    Button buttonTimeEnd;
    Button buttonDateMeeting;
    Button buttonSearchMeeting;

    TextView textViewStartTime;
    TextView textViewEndTime;
    TextView textViewDateMeeting;

    private int hourStrat;
    private int minuteStart;
    private int hourEnd;
    private int minuteEnd;
    private int year;
    private int month;
    private int day;

    static final int TIME_DIALOG_START_ID = 111;
    static final int TIME_DIALOG_END_ID = 222;
    static final int DATE_DIALOG_ID = 333;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);
        addListenerOnButton();

    }

    public void addListenerOnButton() {

        buttonTimeStart = (Button) findViewById(R.id.buttonTimeStart);
        buttonTimeEnd = (Button) findViewById(R.id.buttonTimeEnd);
        buttonDateMeeting = (Button) findViewById(R.id.buttonDateMeeting);
        buttonSearchMeeting = (Button) findViewById(R.id.buttonSearchMeeting);

        // default date meeting
        Calendar calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);
        textViewDateMeeting = (TextView) findViewById(R.id.dateMeeting);
        textViewDateMeeting.setText(new StringBuilder().append(StringUtil.pad(day))
                .append("/").append(StringUtil.pad(month+1)).append("/").append(year)
                .append(" "));

        buttonTimeStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(TIME_DIALOG_START_ID);
            }
        });

        buttonTimeEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(TIME_DIALOG_END_ID);
            }
        });

        buttonDateMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });

//        buttonSearchMeeting.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "Test xxx", Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case TIME_DIALOG_START_ID:
                // set time picker as current time
                return new TimePickerDialog(this, timePickerStartListener, hourStrat, minuteStart, false);
            case TIME_DIALOG_END_ID:
                return new TimePickerDialog(this, timePickerEndListener, hourEnd, minuteEnd, false);
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, datePickerMeetingListener, year, month, day);
        }
        return null;
    }

    private TimePickerDialog.OnTimeSetListener timePickerStartListener = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {

            hourStrat = selectedHour;
            minuteStart = selectedMinute;

            textViewStartTime = (TextView) findViewById(R.id.startTime);
            textViewStartTime.setText(new StringBuilder().append(StringUtil.pad(hourStrat)).append(":").append(StringUtil.pad(minuteStart)));

        }
    };

    private TimePickerDialog.OnTimeSetListener timePickerEndListener = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {

            hourEnd = selectedHour;
            minuteEnd = selectedMinute;

            textViewEndTime = (TextView) findViewById(R.id.endTime);
            textViewEndTime.setText(new StringBuilder().append(StringUtil.pad(hourEnd)).append(":").append(StringUtil.pad(minuteEnd)));

        }
    };

    private DatePickerDialog.OnDateSetListener datePickerMeetingListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            textViewDateMeeting = (TextView) findViewById(R.id.dateMeeting);
            textViewDateMeeting.setText(new StringBuilder().append(StringUtil.pad(day))
                    .append("/").append(StringUtil.pad(month+1)).append("/").append(year)
                    .append(" "));

        }
    };


    public void searchAction(View view){

        TextView dateMeeting = (TextView) findViewById(R.id.dateMeeting);
        TextView startTime = (TextView) findViewById(R.id.startTime);
        TextView endTime = (TextView) findViewById(R.id.endTime);
        TextView member = (TextView) findViewById(R.id.member);
        CheckBox projector = (CheckBox) findViewById(R.id.projector);
        CheckBox conference = (CheckBox) findViewById(R.id.conference);
        CheckBox whiteBoard = (CheckBox) findViewById(R.id.whiteBoard);

        EditText topic = (EditText) findViewById(R.id.topic);
        EditText userIdMeeting = (EditText) findViewById(R.id.userIdMeeting);
        EditText userPhoneMeeting = (EditText) findViewById(R.id.userPhoneMeeting);


        String dateMeetingStr = dateMeeting.getText().toString();
        String startTimeStr = startTime.getText().toString();
        String endTimeStr = endTime.getText().toString();
        String memberStr = member.getText().toString();
        String topicStr = topic.getText().toString().trim();
        String userIdMeetingStr = userIdMeeting.getText().toString().trim();


        if(memberStr.isEmpty() || userIdMeetingStr.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill all *", Toast.LENGTH_SHORT).show();
            return;
        }


        RoomReq roomReq = new RoomReq();
        roomReq.setToken(TokenUtil.getToken(getApplicationContext()));
        roomReq.setUser(TokenUtil.getUser(getApplicationContext()));
        roomReq.setFrom(StringUtil.formatDatTime(dateMeetingStr, startTimeStr)); // real
        roomReq.setTo(StringUtil.formatDatTime(dateMeetingStr, endTimeStr)); // real

        System.out.println("roomReq.getFrom() = "+roomReq.getFrom());
        System.out.println("roomReq.getTo() = "+roomReq.getTo());
        //roomReq.setFrom("20170120 090000"); // test
        //roomReq.setTo("20170120 103000"); // test

        //roomReq.setFrom("20170122 120000"); // test
        //roomReq.setTo("20170122 130000"); // test

        roomReq.setSize(Integer.valueOf(memberStr));
        roomReq.setInverse(true);
        roomReq.setHasProjector(projector.isChecked());
        roomReq.setHasVc(conference.isChecked());
        //roomReq.setHasVc(true); // test
        roomReq.setHasWb(whiteBoard.isChecked());
        //roomReq.setHasWb(true); //test

        Gson gson = new Gson();
//        String json = gson.toJson(roomReq);
//        System.out.println("json request = "+json);

        String json = bowlingJson(roomReq);
        System.out.println("json request = "+json);

        String availableRoom = callSlotAvailable(json);
        System.out.println("availableRoom = "+availableRoom);

        //String jsonInString = "{\"token\":\"mkyong\",\"error\":7500,\"slots\":[{\"roomName\": \"0803\",\"sizeMax\":7},{\"roomName\": \"0814\",\"sizeMax\":9}]}";
        //Type founderType = new TypeToken<LoginRes>(){}.getType();
        //LoginRes loginRes = gson.fromJson(jsonInString, founderType);

        RoomRes res = gson.fromJson(availableRoom, RoomRes.class);// Convert JSON String to car object
        TokenUtil.saveToken(res.getToken(), getApplicationContext());

        if(res.getSlots()!=null && res.getSlots().length == 0){
            Toast.makeText(getApplicationContext(), "Meeting room not found", Toast.LENGTH_SHORT).show();
            return;
        }


//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            RoomRes employe = mapper.readValue(jsonInString,RoomRes.class);
//            System.out.println(employe.getToken());
//            System.out.println(employe.getSlots().length);
//            System.out.println(employe.getSlots()[0].getRoomName());
//            System.out.println(employe.getSlots()[0].getSizeMax());
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

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
                roomList.add(room);
            }
        }

        ReserveInfo info = new ReserveInfo();
        info.setTopic(topic.getText().toString());
        info.setUserIdMeeting(userIdMeeting.getText().toString());
        info.setUserPhoneMeeting(userPhoneMeeting.getText().toString());
        info.setDateMeeting(dateMeetingStr.trim());
        info.setTimeStart(startTimeStr.trim());
        info.setTimeEnd(endTimeStr.trim());

        //AppContext.getInstance().setRoomList(roomList);

        Intent intent = new Intent(ReserveActivity.this,ReserveListActivity.class);
        intent.putExtra("roomList",roomList);
        intent.putExtra("reserveInfo",info);
        startActivity(intent);
    }

    private String bowlingJson(RoomReq roomReq) {
        String req =  "{"
                + "'Token':'"+roomReq.getToken()+"' ,"
                + "'User':'" + roomReq.getUser() + "',"
                + "'Size':'" + roomReq.getSize() + "',"
                + "'From':'" + roomReq.getFrom() + "',"
                + "'To':'" + roomReq.getTo() + "',"
                + "'Inverse':'true'";
                if(roomReq.isHasProjector()){
                    req += ",'HasProjector':'true'";
                }
                if(roomReq.isHasVc()){
                    req += ",'HasVC':'true'";
                }
                if(roomReq.isHasWb()){
                    req += ",'HasWB':'true'";
                }
                req += "}";
        return req;
    }


    private String callSlotAvailable(String json) {
        try {
            DownloadTask task = new DownloadTask();
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

    public String bowlingJson(String name, String password) {
        return "{"
                + "'token' : '' ,"
                + "'name':'" + name + "',"
                + "'pass':'" + password + "',"
                + "}";
    }

}
