package com.example.myapplication.model;

/**
 * Created by kanit.ko on 19/Jan/2017.
 */

public class Room {

    private String Room;
    private int SizeMax;
    private boolean HasProjector;
    private boolean HasVC;
    private boolean HasWB;
    private String sizez;

    public String getRoom() {
        return Room;
    }

    public void setRoom(String room) {
        Room = room;
    }

    public int getSizeMax() {
        return SizeMax;
    }

    public void setSizeMax(int sizeMax) {
        SizeMax = sizeMax;
    }

    public boolean isHasProjector() {
        return HasProjector;
    }

    public void setHasProjector(boolean hasProjector) {
        HasProjector = hasProjector;
    }

    public boolean isHasVC() {
        return HasVC;
    }

    public void setHasVC(boolean hasVC) {
        HasVC = hasVC;
    }

    public boolean isHasWB() {
        return HasWB;
    }

    public void setHasWB(boolean hasWB) {
        HasWB = hasWB;
    }

    public String getSizez() {
        return sizez;
    }

    public void setSizez(String sizez) {
        this.sizez = sizez;
    }
}
