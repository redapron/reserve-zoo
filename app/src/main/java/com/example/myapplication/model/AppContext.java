package com.example.myapplication.model;

import java.util.List;

/**
 * Created by kanit.ko on 20/Jan/2017.
 */

public class AppContext {

    private List<Room> roomList;
    private ReserveInfo reserveInfo;
    private static AppContext instance;

    private AppContext(){

    }

    public static AppContext getInstance() {
        if (instance == null) {
            instance = new AppContext();
        }
        return instance;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

    public ReserveInfo getReserveInfo() {
        return reserveInfo;
    }

    public void setReserveInfo(ReserveInfo reserveInfo) {
        this.reserveInfo = reserveInfo;
    }
}
