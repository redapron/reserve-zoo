package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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
}
