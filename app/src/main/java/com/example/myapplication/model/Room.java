package com.example.myapplication.model;

/**
 * Created by kanit.ko on 19/Jan/2017.
 */

public class Room {

    private String roomname;
    private int sizemax;
    private boolean hasprojector;
    private boolean hasvc;
    private boolean haswb;

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    public int getSizemax() {
        return sizemax;
    }

    public void setSizemax(int sizemax) {
        this.sizemax = sizemax;
    }

    public boolean isHasprojector() {
        return hasprojector;
    }

    public void setHasprojector(boolean hasprojector) {
        this.hasprojector = hasprojector;
    }

    public boolean isHasvc() {
        return hasvc;
    }

    public void setHasvc(boolean hasvc) {
        this.hasvc = hasvc;
    }

    public boolean isHaswb() {
        return haswb;
    }

    public void setHaswb(boolean haswb) {
        this.haswb = haswb;
    }
}
