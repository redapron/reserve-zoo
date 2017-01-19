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

import com.example.myapplication.model.LoginRes;
import com.example.myapplication.util.TokenUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TokenUtil.saveToken("", getApplicationContext());
        String ccc = TokenUtil.getToken(getApplicationContext());
        if(ccc.isEmpty()){
            setContentView(R.layout.activity_main);
        } else {
            setContentView(R.layout.activity_centerpoints);
        }
    }

    public void doLogin(View view){

        String user = "Android3";  // test
        String pass = "66y41168j";  // test

        String loginResult = callLogin(user ,pass);
        System.out.println("loginResult = "+loginResult);

        Gson gson = new Gson();
        Type founderType = new TypeToken<LoginRes>(){}.getType();
        LoginRes loginRes = gson.fromJson(loginResult, founderType);
        if(loginRes != null && loginRes.getToken()!= null){
            TokenUtil.saveToken(loginRes.getToken(),getApplicationContext());
            Intent intent = new Intent(this,CenterpointActivity.class);
            intent.putExtra("token",loginRes.getToken());
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Invalid Username/Password", Toast.LENGTH_SHORT).show();
        }


//        Intent intent = new Intent(this,CenterpointActivity.class);
//        intent.putExtra("name3","xxx");
//        intent.putExtra("name4","yyy");
//        startActivity(intent);
    }
	
	private String callLogin(String user, String password){
        try {
            DownloadTask task = new DownloadTask();
            task.setUrl("http://10.215.101.76:5000/user/login");
            task.setJson(bowlingJson(user, password));
            String result = task.execute().get();
            return result;
        } catch (InterruptedException e){
            e.printStackTrace();
            return null;
        } catch (Exception e){
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
