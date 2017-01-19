package com.example.myapplication;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by nu on 1/18/2017.
 */
public class DownloadTask extends AsyncTask<String,Void,String> {

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
            String getResponse = post(url, json);
            return getResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

}
