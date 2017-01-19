package com.example.myapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.myapplication.model.LoginRes;
import com.example.myapplication.model.SearchCancelResult;
import com.example.myapplication.util.StringUtil;
import com.example.myapplication.util.TokenUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
        String rtn = textViewDateCancel.getText().toString();

        //Toast.makeText(getApplicationContext(), rtn, Toast.LENGTH_SHORT).show();
        Log.i("bui", StringUtil.formatDate(rtn));

        Map<String, String> m = new HashMap<>();
        m.put("token", TokenUtil.getToken(getApplicationContext()));
        m.put("user", "Android4");
        m.put("from", "20170120 090000");
        m.put("to", "20170120 103000");

        callSearch(m);
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

    private String callSearch(Map<String, String> m){
        try {
            DownloadTask task = new DownloadTask();
            task.setUrl("http://10.215.101.76:5000/slot/view");
            task.setJson(makeJson(m));
            String result = task.execute().get();


            Gson gson = new Gson();
            Type founderType = new TypeToken<SearchCancelResult>(){}.getType();
            SearchCancelResult rs = gson.fromJson(result, founderType);

            TokenUtil.saveToken(rs.getToken(), getApplicationContext());

            Toast.makeText(getApplicationContext(), rs.getSlots(), Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();

            return result;
        } catch (InterruptedException e){
            e.printStackTrace();
            return null;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
