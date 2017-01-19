package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.model.LoginRes;
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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TokenUtil.saveToken("", getApplicationContext()); // test
        String lastToken = TokenUtil.getToken(getApplicationContext());
        System.out.println("lastToken = "+lastToken);
        if(lastToken.isEmpty()){
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
            task.setUrl("http://10.215.101.76:5000/user/login");
            Map<String, String> login = new HashMap<>();
            login.put("token", "");
            login.put("name", user);
            login.put("pass", password);
            task.setJson(makeJson(login));
            return task.execute().get();
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

    public void doLogout(View view){
        TokenUtil.saveToken("", getApplicationContext());
        TokenUtil.saveUser("", getApplicationContext());
        Toast.makeText(getApplicationContext(), "Logout Done", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,MainActivity.class);
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
}
