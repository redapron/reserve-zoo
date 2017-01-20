package com.example.myapplication.model;

import java.io.Serializable;

/**
 * Created by kanit.ko on 19/Jan/2017.
 */

public class Room implements Serializable{

    private String Room;
    private int SizeMax;
    private int SizeMin;
    private boolean HasProjector;
    private boolean HasVC;
    private boolean HasWB;

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

    public int getSizeMin() {
        return SizeMin;
    }

    public void setSizeMin(int sizeMin) {
        SizeMin = sizeMin;
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
}
