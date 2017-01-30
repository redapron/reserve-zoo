package com.example.myapplication.util;

import android.util.Log;

import java.util.Iterator;
import java.util.Map;

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
        String[] eee  = hhmm.split("[:]");
        String dd = ddd[2].trim()+ddd[1]+ddd[0]+" "+eee[0]+eee[1]+"00";
        return dd;
    }

    public static String getClientDateFormat(String yyyyMMdd_hhmmss){
        if(yyyyMMdd_hhmmss != null){
            yyyyMMdd_hhmmss = yyyyMMdd_hhmmss.trim();
            String[] xxx = yyyyMMdd_hhmmss.split("[ ]");
            //20170131
            String dateOnly = xxx[0];
            String date = dateOnly.substring(6,8)+"/"+dateOnly.substring(4,6)+"/"+dateOnly.substring(0,4);
            return date;
        }
        return "";
    }

    public static String getClientTimeFormat(String yyyyMMdd_hhmmss){
        if(yyyyMMdd_hhmmss != null){
            yyyyMMdd_hhmmss = yyyyMMdd_hhmmss.trim();
            String[] xxx = yyyyMMdd_hhmmss.split("[ ]");
            //20170131
            String timeOnly = xxx[1];
            String time = timeOnly.substring(0,2)+":"+timeOnly.substring(2,4);
            return time;
        }
        return "";
    }

    public static String makeJson(Map<String,String> m) {
        Iterator<Map.Entry<String, String>> it = m.entrySet().iterator();
        String rtn = "{";
        while (it.hasNext()) {
            Map.Entry<String, String> pair =  it.next();
            rtn += String.format("'%s': '%s', ", pair.getKey(), pair.getValue());
        }
        rtn += "}";
        Log.i("makeJson", rtn);
        return rtn;
    }

    public static void main(String[] arg ){
        String xx = getClientDateFormat("20170131 123456");
        String yy = getClientTimeFormat("20170131 123456");
        System.out.println("xx = "+ xx);
        System.out.println("yy = "+ yy);
    }

}
