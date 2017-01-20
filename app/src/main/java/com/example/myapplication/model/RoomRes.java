package com.example.myapplication.model;

/**
 * Created by kanit.ko on 19/Jan/2017.
 */

public class RoomRes {

    private String Token;
    private String Error;
    private int State;
    private Room[] Slots;

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

    public Room[] getSlots() {
        return Slots;
    }

    public void setSlots(Room[] slots) {
        Slots = slots;
    }
}
