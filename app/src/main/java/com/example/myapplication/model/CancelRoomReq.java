package com.example.myapplication.model;

/**
 * Created by bui on 1/20/2017.
 */

public class CancelRoomReq {

    private String Token;
    private String User;
    private String From;
    private String To;

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getFrom() {
        return From;
    }

    public void setFrom(String from) {
        From = from;
    }

    public String getTo() {
        return To;
    }

    public void setTo(String to) {
        To = to;
    }
}
