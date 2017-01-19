package com.example.myapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

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
        textViewDateMeeting.setText(new StringBuilder().append(pad(day))
                .append("/").append(pad(month+1)).append("/").append(year)
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
            textViewStartTime.setText(new StringBuilder().append(pad(hourStrat)).append(":").append(pad(minuteStart)));

        }
    };

    private TimePickerDialog.OnTimeSetListener timePickerEndListener = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {

            hourEnd = selectedHour;
            minuteEnd = selectedMinute;

            textViewEndTime = (TextView) findViewById(R.id.endTime);
            textViewEndTime.setText(new StringBuilder().append(pad(hourEnd)).append(":").append(pad(minuteEnd)));

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
            textViewDateMeeting.setText(new StringBuilder().append(pad(day))
                    .append("/").append(pad(month+1)).append("/").append(year)
                    .append(" "));

        }
    };

    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

    public void searchAction(View view){
        Intent intent = new Intent(this,SearchResultActivity.class);
        intent.putExtra("name","xxx");
        intent.putExtra("name2","yyy");
        startActivity(intent);
    }



}
