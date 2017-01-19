package com.example.myapplication.util;

public class StringUtil {
    public static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

    public static String formatDate(String d) {
        String[] ds = d.split("/");
        if (ds.length != 3) {
            return "";
        }
        return String.format("%s%s%s", ds[2], ds[1], ds[0]);
    }
}
