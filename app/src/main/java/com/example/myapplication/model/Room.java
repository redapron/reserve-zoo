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

    private String Place;
    private int Floor;
    private int Number;
    private String From;
    private String To;
    private String User;
    private String UserEN;
    private String UserTH;
    private String Phone;
    private String Email;
    private String ForUser;
    private String ForUserEN;
    private String ForUserTH;
    private String ForPhone;
    private String ForEmail;
    private String Note;
    private String When;

    public String getPlace() {
        return Place;
    }

    public void setPlace(String place) {
        Place = place;
    }

    public int getFloor() {
        return Floor;
    }

    public void setFloor(int floor) {
        Floor = floor;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
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

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getUserEN() {
        return UserEN;
    }

    public void setUserEN(String userEN) {
        UserEN = userEN;
    }

    public String getUserTH() {
        return UserTH;
    }

    public void setUserTH(String userTH) {
        UserTH = userTH;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getForUser() {
        return ForUser;
    }

    public void setForUser(String forUser) {
        ForUser = forUser;
    }

    public String getForUserEN() {
        return ForUserEN;
    }

    public void setForUserEN(String forUserEN) {
        ForUserEN = forUserEN;
    }

    public String getForUserTH() {
        return ForUserTH;
    }

    public void setForUserTH(String forUserTH) {
        ForUserTH = forUserTH;
    }

    public String getForPhone() {
        return ForPhone;
    }

    public void setForPhone(String forPhone) {
        ForPhone = forPhone;
    }

    public String getForEmail() {
        return ForEmail;
    }

    public void setForEmail(String forEmail) {
        ForEmail = forEmail;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public String getWhen() {
        return When;
    }

    public void setWhen(String when) {
        When = when;
    }

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
