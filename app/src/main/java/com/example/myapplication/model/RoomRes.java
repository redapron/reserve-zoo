package com.example.myapplication.model;

/**
 * Created by kanit.ko on 19/Jan/2017.
 */

public class RoomRes {

    private String token;
    private String error;
    private int state;
    private Room[] slots;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Room[] getSlots() {
        return slots;
    }

    public void setSlots(Room[] slots) {
        this.slots = slots;
    }
}
