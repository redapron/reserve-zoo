package com.example.myapplication.model;

/**
 * Created by kanit.ko on 19/Jan/2017.
 */

public class Room {

    private String room;
    private String sizez;
    private int sizemax;
    private boolean hasprojector;
    private boolean hasvc;
    private boolean haswb;

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
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

    public String getSizez() {
        return sizez;
    }

    public void setSizez(String sizez) {
        this.sizez = sizez;
    }
}
