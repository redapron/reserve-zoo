package com.example.myapplication.model;

import java.io.Serializable;

/**
 * Created by kanit.ko on 20/Jan/2017.
 */

public class ReserveInfo implements Serializable{

    private String dateMeeting;
    private String timeStart;
    private String timeEnd;
    private String topic;
    private String userIdMeeting;
    private String userPhoneMeeting;
    private String room;
    private int sizeMin;
    private int sizeMax;

    public String getDateMeeting() {
        return dateMeeting;
    }

    public void setDateMeeting(String dateMeeting) {
        this.dateMeeting = dateMeeting;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getUserIdMeeting() {
        return userIdMeeting;
    }

    public void setUserIdMeeting(String userIdMeeting) {
        this.userIdMeeting = userIdMeeting;
    }

    public String getUserPhoneMeeting() {
        return userPhoneMeeting;
    }

    public void setUserPhoneMeeting(String userPhoneMeeting) {
        this.userPhoneMeeting = userPhoneMeeting;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getSizeMin() {
        return sizeMin;
    }

    public void setSizeMin(int sizeMin) {
        this.sizeMin = sizeMin;
    }

    public int getSizeMax() {
        return sizeMax;
    }

    public void setSizeMax(int sizeMax) {
        this.sizeMax = sizeMax;
    }
}
