package com.example.myapplication;

import android.os.AsyncTask;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by bui on 1/20/2017.
 */

public class DeleteTask extends AsyncTask<String,Void,String> {

    OkHttpClient client;
    MediaType JSON;

    private String url;
    private String json;

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    protected String doInBackground(String... urls) {
        try {
            client = new OkHttpClient();
            JSON = MediaType.parse("application/json; charset=utf-8");
            String getResponse = delete(url, json);
            return getResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String delete(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .delete(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

}
