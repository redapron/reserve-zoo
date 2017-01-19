package com.example.myapplication.model;

/**
 * Created by kanit.ko on 19/Jan/2017.
 */

public class RoomReq {

    private String Token;
    private String User;
    private String From;
    private String To;
    private int Size;
    private boolean Invert;
    private boolean HasProjector;
    private boolean HasVc;
    private boolean HasWb;

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
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

    public int getSize() {
        return Size;
    }

    public void setSize(int size) {
        Size = size;
    }

    public boolean isInvert() {
        return Invert;
    }

    public void setInvert(boolean invert) {
        Invert = invert;
    }

    public boolean isHasProjector() {
        return HasProjector;
    }

    public void setHasProjector(boolean hasProjector) {
        HasProjector = hasProjector;
    }

    public boolean isHasVc() {
        return HasVc;
    }

    public void setHasVc(boolean hasVc) {
        HasVc = hasVc;
    }

    public boolean isHasWb() {
        return HasWb;
    }

    public void setHasWb(boolean hasWb) {
        HasWb = hasWb;
    }
}
