package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.config.Constant;
import com.example.myapplication.model.LoginRes;
import com.example.myapplication.util.StringUtil;
import com.example.myapplication.util.TokenUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnReserve;
    Button btnCancel;
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TokenUtil.saveToken("", getApplicationContext()); // test
        String lastToken = TokenUtil.getToken(getApplicationContext());
        System.out.println("lastToken = "+lastToken);
        if(lastToken.isEmpty()){
            setContentView(R.layout.activity_main);
            String lastLogin = TokenUtil.getUser(getApplicationContext());
            if(!lastLogin.isEmpty()){
                EditText userBox = (EditText) findViewById(R.id.userBox);
                userBox.setText(lastLogin);
            }
        } else {
            setContentView(R.layout.activity_centerpoints);
            btnReserve = (Button) findViewById(R.id.reserveButton);
            btnReserve.setOnClickListener(this);
            btnCancel = (Button) findViewById(R.id.cancelReserveButton);
            btnCancel.setOnClickListener(this);
            btnLogout = (Button) findViewById(R.id.logoutButton);
            btnLogout.setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View v) {
        if (v == btnReserve){
            Intent intent = new Intent(MainActivity.this,ReserveActivity.class);
            startActivity(intent);
            Log.i("nu","111");
        }
        if (v == btnCancel){
            Intent intent = new Intent(MainActivity.this,CancelActivity.class);
            intent.putExtra("name3","xxx");
            intent.putExtra("name4","yyy");
            startActivity(intent);
            Log.i("nu","222");
        }
        if (v == btnLogout){
            TokenUtil.saveToken("", getApplicationContext());
            //TokenUtil.saveUser("", getApplicationContext());
            Toast.makeText(getApplicationContext(), "Logout Done", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this,MainActivity.class);
            startActivity(intent);
            Log.i("nu","333");
        }
    }

    public void doLogin(View view){
//        Toast.makeText(getApplicationContext(), "Meeting room not found", Toast.LENGTH_LONG).show();  // test
//        Intent intent = new Intent(this,CenterpointActivity.class); // test
//        startActivity(intent); // test

        EditText userBox = (EditText) findViewById(R.id.userBox);
        EditText passwordBox = (EditText) findViewById(R.id.passwordBox);
        String user = userBox.getText().toString().trim();
        String pass = passwordBox.getText().toString().trim();

//        user = "Android3";  // test
//        pass = "66y41168j";  // test

        String loginResult = callLogin(user ,pass);
        if(loginResult==null){
            Toast.makeText(getApplicationContext(), "Cannot connect to server, Please try again.", Toast.LENGTH_LONG).show();
            return;
        }
        System.out.println("loginResult = "+loginResult);

        Gson gson = new Gson();
        Type founderType = new TypeToken<LoginRes>(){}.getType();
        LoginRes loginRes = gson.fromJson(loginResult, founderType);
            if(loginRes != null && loginRes.getToken()!= null){
                TokenUtil.saveToken(loginRes.getToken(),getApplicationContext());
                TokenUtil.saveUser(user,getApplicationContext());
                Intent intent = new Intent(this,CenterpointActivity.class);
                startActivity(intent);
            } else {
            Toast.makeText(getApplicationContext(), "Invalid Username/Password", Toast.LENGTH_SHORT).show();
        }

    }

	private String callLogin(String user, String password){
        try {
            DownloadTask task = new DownloadTask();
            task.setUrl(Constant.SERVICE_USER_LOGIN);
            Map<String, String> login = new HashMap<>();
            login.put("token", "");
            login.put("name", user);
            login.put("pass", password);
            task.setJson(StringUtil.makeJson(login));
            return task.execute().get();
        } catch (InterruptedException e){
            e.printStackTrace();
            return null;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void doLogout(View view){
        TokenUtil.saveToken("", getApplicationContext());
        //TokenUtil.saveUser("", getApplicationContext());
        Toast.makeText(getApplicationContext(), "Logout Done", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void showAction(View view){
        Intent intent = new Intent(this,ShowActivity.class);
        startActivity(intent);
    }
}
