package com.example.myapplication.util;

import android.util.Log;

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
        return String.format("%s%s%s", ds[2].trim(), ds[1], ds[0]);
    }

    public static String formatDatTime(String ddMMyyyy, String hhmm){
        String[] ddd  = ddMMyyyy.split("[/]");
        String dd = ddd[2].trim()+ddd[1]+ddd[0]+" "+hhmm+"00";
        return dd;
    }

}
