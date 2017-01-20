package com.example.myapplication.model;

/**
 * Created by bui on 1/20/2017.
 */

public class CancelRoomRes {

    private String Token;
    private String Error;
    private int State;

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getError() {
        return Error;
    }

    public void setError(String error) {
        Error = error;
    }

    public int getState() {
        return State;
    }

    public void setState(int state) {
        State = state;
    }
}
