package com.example.myapplication.model;

/**
 * Created by bui on 1/19/2017.
 */

public class SearchCancelResult {
    private String Token;
    private String Error;
    private int State;
    private Slots[] Slots;

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

    public Slots[] getSlots() {
        return Slots;
    }

    public void setSlots(Slots[] slots) {
        Slots = slots;
    }
}

class Slots {
    private String Room;
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
    private String From;
    private String To;
    private int SizeMax;
    private int SizeMin;
    private boolean HasProjector;
    private boolean HasVC;
    private boolean HasWB;
    private String When;

    public String getRoom() {
        return Room;
    }

    public void setRoom(String room) {
        Room = room;
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

    public String getWhen() {
        return When;
    }

    public void setWhen(String when) {
        When = when;
    }
}
