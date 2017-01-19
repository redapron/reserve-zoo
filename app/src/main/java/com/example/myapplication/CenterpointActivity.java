package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.model.LoginRes;
import com.example.myapplication.util.TokenUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class CenterpointActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_centerpoints);
    }

    public void reserveAction(View view){
        Intent intent = new Intent(this,ReserveActivity.class);
        intent.putExtra("name3","xxx");
        intent.putExtra("name4","yyy");
        startActivity(intent);
    }

    public void doLogout(View view){
        TokenUtil.saveToken("", getApplicationContext());
        Toast.makeText(getApplicationContext(), "Logout Done", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
