package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.util.TokenUtil;

public class CenterpointActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_centerpoints);
    }

    public void reserveAction(View view){
        Intent intent = new Intent(this,ReserveActivity.class);
        startActivity(intent);
    }

    public void cancelAction(View view){
        Intent intent = new Intent(this,CancelActivity.class);
        startActivity(intent);
    }

    public void showAction(View view){
        Intent intent = new Intent(this,ShowActivity.class);
        startActivity(intent);
    }

    public void doLogout(View view){
        TokenUtil.saveToken("", getApplicationContext());
        //TokenUtil.saveUser("", getApplicationContext());
        Toast.makeText(getApplicationContext(), "Logout Done", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
